import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class parser {

    @Test
    public void parserJavaFile()
    {
        File file = new File("/home/haku/IdeaProjects/DDP_UET/examples/Builder Example/ItalianMealBuilder.java");
        try {
            CompilationUnit compilationUnit = JavaParser.parse(file);

            ClassOrInterfaceDeclaration type = (ClassOrInterfaceDeclaration) compilationUnit.getType(0);
            System.out.println(type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

