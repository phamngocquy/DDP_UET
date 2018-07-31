import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class parser {

    @Test
    public void parserJavaFile() {
        File file = new File("C:\\Users\\Haku\\IdeaProjects\\DDP_UET\\examples\\Observer Example\\MyTopic.java");
        try {
            CompilationUnit compilationUnit = JavaParser.parse(file);
            TypeDeclaration declaration = compilationUnit.getType(0);
            List<BodyDeclaration> list = declaration.getMembers();
            for (BodyDeclaration bodyDeclaration : list) {
                if (bodyDeclaration.isMethodDeclaration()) {
                    MethodDeclaration aaa = (MethodDeclaration) bodyDeclaration;
                    System.out.println(aaa.getBegin().toString());
                }
            }
            compilationUnit.accept(new VoidVisitorAdapter<Void>() {
                @Override
                public void visit(MethodDeclaration n, Void arg) {
//                    System.out.println(n.getBody().toString());
//                    System.out.println(n.getMetaModel());
                    super.visit(n, arg);
                }
            }, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

