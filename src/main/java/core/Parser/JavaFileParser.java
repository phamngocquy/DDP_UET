package core.Parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import core.dom.JavaClassNode;
import core.dom.JavaFileNode;
import core.dom.Node;
import core.util.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;

public class JavaFileParser implements IParser {

    private JavaFileNode javaFileNode;

    public void parse(final Node rootNode) {
        try {
            this.javaFileNode = (JavaFileNode) rootNode;

            CompilationUnit compilationUnit = JavaParser.parse(new File(rootNode.getAbsolutePath()));
            compilationUnit.accept(new VoidVisitorAdapter<Void>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Void arg) {

                    JavaClassNode classNode = new JavaClassNode();
                    classNode.setModifiers(n.getModifiers());
                    classNode.setName(n.getNameAsString());
                    classNode.setAbsolutePath(FileHelper.getAbsolutePath(rootNode.getAbsolutePath(),
                            n.getNameAsString()));


                    super.visit(n, arg);
                }
            }, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void javaClassParse(ClassOrInterfaceDeclaration javaClassNode) {
        javaClassNode.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
                super.visit(n, arg);
            }

            @Override
            public void visit(FieldDeclaration n, Void arg) {
                super.visit(n, arg);
            }
        }, null);
    }


}
