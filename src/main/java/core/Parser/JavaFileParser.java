package core.Parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import core.constant.JavaChildrenType;
import core.constant.JavaClassType;
import core.constant.JavaPolymorphismType;
import core.dom.*;
import core.model.JavaParameter;
import core.model.Polymorphism;
import core.util.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class JavaFileParser implements IParser {

    private JavaFileNode javaFileNode;

    public void parse(Node rootNode) {
        try {
            this.javaFileNode = (JavaFileNode) rootNode;

            CompilationUnit compilationUnit = JavaParser.parse(new File(rootNode.getAbsolutePath()));
            compilationUnit.accept(new VoidVisitorAdapter<Void>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Void arg) {

                    JavaClassNode classNode = new JavaClassNode();
                    classNode.setModifiers(n.getModifiers());
                    classNode.setName(n.getNameAsString());
                    classNode.setAbsolutePath(FileHelper.getAbsolutePath(javaFileNode.getAbsolutePath(),
                            n.getNameAsString()));
                    classNode.setAbstract(n.isAbstract());
                    classNode.setInterface(n.isInterface());
                    classNode.setPolymorphismList(getPolymorphismProperties(n));

                    // Java Class Type
                    if (n.isInterface()) classNode.setType(JavaClassType.I);
                    else if (n.isAbstract()) classNode.setType(JavaClassType.A);
                    else if (n.isGeneric()) classNode.setType(JavaClassType.T);
                    else classNode.setType(JavaClassType.C);

                    classNode.setParent(javaFileNode);
                    javaFileNode.addChild(classNode);

                    // parser child  class java
                    javaClassParse(n, classNode);
                    super.visit(n, arg);
                }
            }, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void javaClassParse(ClassOrInterfaceDeclaration javaClassNode, final JavaClassNode classNode) {
        javaClassNode.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
                JavaMethodNode node = new JavaMethodNode();
                node.setModifiers(n.getModifiers());
                node.setName(n.getNameAsString());
                node.setAbsolutePath(FileHelper.getAbsolutePath(classNode.getAbsolutePath(), node.getName()));
                node.setReturnType(n.getType().toString());
                node.setBody(n.getBody().toString());
                parseParameter(node, n);
                classNode.addChild(node);
                node.setParent(classNode);
                super.visit(n, arg);
            }

            @Override
            public void visit(FieldDeclaration n, Void arg) {
                JavaFieldNode node = new JavaFieldNode();
                node.setModifiers(n.getModifiers());
                node.setName(n.getVariable(0).toString());
                node.setValue_type(n.getCommonType().toString());
                node.setAbsolutePath(FileHelper.getAbsolutePath(classNode.getAbsolutePath(), node.getName()));
                classNode.addChild(node);
                node.setParent(classNode);
                super.visit(n, arg);
            }
        }, null);
    }

    private void parseParameter(JavaMethodNode javaMethodNode, MethodDeclaration n) {
        List<JavaParameter> parameters = new ArrayList<>();
        for (Parameter p : n.getParameters()) {
            parameters.add(new JavaParameter(p.getNameAsString(), p.getTypeAsString()));
        }
        javaMethodNode.setParameterList(parameters);
    }

    private static List<Polymorphism> getPolymorphismProperties(ClassOrInterfaceDeclaration declaration) {
        List<Polymorphism> result = new ArrayList<>();
        for (ClassOrInterfaceType type : declaration.getImplementedTypes()) {
            result.add(new Polymorphism(type.getNameAsString(), JavaPolymorphismType.IMPLEMENTS));
        }
        for (ClassOrInterfaceType type : declaration.getExtendedTypes()) {
            result.add(new Polymorphism(type.getNameAsString(), JavaPolymorphismType.EXTENDS));
        }

        return result;
    }
}
