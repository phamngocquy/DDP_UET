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
        File file = new File("/home/jcia/IdeaProjects/DP-CORE-master/examples/Bridge Example/SamsungTV.java");
        try {
            CompilationUnit compilationUnit = JavaParser.parse(file);
            System.out.println(compilationUnit.toString());
            ClassOrInterfaceDeclaration type = (ClassOrInterfaceDeclaration) compilationUnit.getType(0);
            NodeList<ClassOrInterfaceType> interfaceTypes = type.getImplementedTypes();
            System.out.println(interfaceTypes);

            NodeList<TypeParameter> interfaceTypes_1 = type.getTypeParameters();
            System.out.println(interfaceTypes_1);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

