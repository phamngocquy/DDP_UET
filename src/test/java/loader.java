import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import core.exception.D2pNotFoundException;
import core.loader.LoaderImpl;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class loader {

    @Test
    public void loadListJavFile() throws FileNotFoundException {
        String projectPath = "/home/jcia/IdeaProjects/DP-CORE-master/examples/AbstractFactoryExample";
        File folder = new File(projectPath);
        File[] files = folder.listFiles();
        List<CompilationUnit> unitList = new ArrayList<CompilationUnit>();

        for (File file : files){
            if(file.isFile())
            {
                CompilationUnit compilationUnit = JavaParser.parse(new File(file.getAbsolutePath()));
                unitList.add(compilationUnit);

                ClassOrInterfaceDeclaration type = (ClassOrInterfaceDeclaration) compilationUnit.getType(0);

                NodeList<ClassOrInterfaceType> listInterface = type.getImplementedTypes();

                NodeList<ClassOrInterfaceType> listExtends = type.getExtendedTypes();

                System.out.println("aaa: "+compilationUnit.getPrimaryTypeName());
                System.out.println("class: "+ type.getNameAsString());
                System.out.println("File path: " +file.getAbsolutePath());
                System.out.println("Interface class: "+ listInterface.toString());
                System.out.println("Extends class: " + listExtends.toString());
                System.out.println("/////////////////////////////////");
            }
        }
    }
    @Test
    public void testLoadProject() throws D2pNotFoundException {
        LoaderImpl loader = new LoaderImpl();
        loader.load("C:\\Users\\Haku\\IdeaProjects\\DDP_UET\\examples\\Builder Example");
//            loader.load("../DDP_UET/examples/Builder Example");
    }

}
