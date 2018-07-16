package core.Parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import core.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;

public class JavaFileParser implements IParser {

    public void parse(Node node) {
        try {
            CompilationUnit compilationUnit = JavaParser.parse(new File(node.getAbsolutePath()));
            compilationUnit.accept(new VoidVisitorAdapter<Void>() {
                @Override
                public void visit(ClassOrInterfaceDeclaration n, Void arg) {

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
