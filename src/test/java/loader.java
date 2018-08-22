import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import core.analyzer.JavaCoreAnalyzer;
import core.dependency.Dependency;
import core.dom.JavaClassNode;
import core.dom.Node;
import core.exception.D2pNotFoundException;
import core.helper.Search;
import core.loader.LoaderImpl;
import core.util.JsonHelper;
import org.jgrapht.alg.isomorphism.IsomorphicGraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class loader {

    @Test
    public void loadListJavFile() throws FileNotFoundException {
        String projectPath = "/home/jcia/IdeaProjects/DP-CORE-master/examples/AbstractFactoryExample";
        File folder = new File(projectPath);
        File[] files = folder.listFiles();
        List<CompilationUnit> unitList = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()) {
                CompilationUnit compilationUnit = JavaParser.parse(new File(file.getAbsolutePath()));
                unitList.add(compilationUnit);

                ClassOrInterfaceDeclaration type = (ClassOrInterfaceDeclaration) compilationUnit.getType(0);

                NodeList<ClassOrInterfaceType> listInterface = type.getImplementedTypes();

                NodeList<ClassOrInterfaceType> listExtends = type.getExtendedTypes();

                System.out.println("aaa: " + compilationUnit.getPrimaryTypeName());
                System.out.println("class: " + type.getNameAsString());
                System.out.println("File path: " + file.getAbsolutePath());
                System.out.println("Interface class: " + listInterface.toString());
                System.out.println("Extends class: " + listExtends.toString());
                System.out.println("/////////////////////////////////");
            }
        }
    }

    @Test
    public void testVF2Agl() {
//        VF2SubgraphIsomorphismInspector inspector = new VF2SubgraphIsomorphismInspector()
    }

    @Test
    public void testLoadProject() throws D2pNotFoundException {
        LoaderImpl loader = new LoaderImpl();
//        loader.load("C:\\Users\\Haku\\IdeaProjects\\DDP_UET\\examples\\Bridge Example");
        loader.load("/home/haku/DDP/ddp/DDP_UET/examples/Combined Patterns Example");
        Node projectNode = loader.getProjectNode();
        JavaCoreAnalyzer javaCoreAnalyzer = new JavaCoreAnalyzer();
        javaCoreAnalyzer.doAnalyzer(projectNode);
        List<Node> nodes = Search.getAllJavaClassNode(projectNode);
        for (Node iNode : nodes) {
            JavaClassNode javaClassNode = (JavaClassNode) iNode;
            Set<Dependency> dependencies = javaClassNode.getDependencies();
            for (Dependency dependency : dependencies) {
//                System.out.println(dependency.toString());
            }
        }

        System.out.println(JsonHelper.getInstance().getJson(projectNode));

//        JavaCoreAnalyzer javaCoreAnalyzer = new JavaCoreAnalyzer();
//        javaCoreAnalyzer.doAnalyzer(projectNode);

//        List<Node> nodeList = Search.getAllJavaClassNode(loader.getProjectNode());
//        Node node = new Node();
//        node.setChild(nodeList);
//        System.out.println(JsonHelper.getInstance().getJson(node));
    }
}
