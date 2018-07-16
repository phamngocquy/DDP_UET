package core.Parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import core.dom.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class JavaFileParser implements IParser {

    public void parse(Node node) {
        File file = new File(node.getAbsolutePath());
        try {
            CompilationUnit compilationUnit = JavaParser.parse(file);
            NodeList<TypeDeclaration<?>> list = compilationUnit.getTypes();
            for (TypeDeclaration declaration : list) {
                /*
                    Get all properties of class
                 */
                /*

                 */
                parsing(declaration);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parsing(TypeDeclaration type) {
        type.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
                super.visit(n, arg);
            }

            @Override
            public void visit(FieldDeclaration n, Void arg) {
                super.visit(n, arg);
            }
2
        }, null);
    }

}
