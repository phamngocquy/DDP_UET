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
        File file = new File("/home/jcia/IdeaProjects/DP-CORE-master/examples/AbstractFactoryExample/Test.java");
        try {
            CompilationUnit compilationUnit = JavaParser.parse(file);

            ClassOrInterfaceDeclaration type = (ClassOrInterfaceDeclaration) compilationUnit.getType(0);

            ClassOrInterfaceDeclaration type_ = (ClassOrInterfaceDeclaration) compilationUnit.getType(1);

            System.out.println(type);
            System.out.println("////");
            System.out.println(type_);

//            NodeList<ClassOrInterfaceType> interfaceTypes = type.getExtendedTypes();
//            System.out.println(interfaceTypes);
//
//
//
//            System.out.println("/////");
//            System.out.println(type.getChildNodes().toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

