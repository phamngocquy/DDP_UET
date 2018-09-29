import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import core.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import core.alg.isomorphism.edge.DDPEdge;
import core.analyzer.JavaCoreAnalyzer;
import core.constant.JavaTypeDependencies;
import core.dependency.Dependency;
import core.dom.JavaClassNode;
import core.dom.Node;
import core.exception.D2pNotFoundException;
import core.helper.Search;
import core.loader.LoaderImpl;
import core.util.JsonHelper;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
    public void testLoadProject() throws D2pNotFoundException {

        Graph<Node, DefaultEdge> graphOne =
                new DefaultDirectedGraph<>(DefaultEdge.class);

        LoaderImpl loader = new LoaderImpl();
        loader.load("C:\\Users\\Haku\\IdeaProjects\\DDP_UET\\examples\\Visitor Example");
        Node projectNode = loader.getProjectNode();
        JavaCoreAnalyzer javaCoreAnalyzer = new JavaCoreAnalyzer();
        javaCoreAnalyzer.doAnalyzer(projectNode);
        List<Node> nodes = Search.getAllJavaClassNode(projectNode);
        Set<Dependency> dependencies = new HashSet<>();
        for (Node iNode : nodes) {
            JavaClassNode javaClassNode = (JavaClassNode) iNode;
            dependencies.addAll(javaClassNode.getDependencies());
        }
        Set<Node> nodeSet = new HashSet<>();
        for (Dependency dependency : dependencies) {
            nodeSet.add(dependency.getFrom());
            nodeSet.add(dependency.getTo());
        }
        for (Node node : nodeSet) {
            graphOne.addVertex(node);
            System.out.println("Vertex Caller: " + node.getName());
        }
        for (Dependency dependency : dependencies) {
            graphOne.addEdge(dependency.getFrom(), dependency.getTo());
            System.out.println("add EDGE: " + dependency.getFrom().getName() + " -- " + dependency.getTo().getName());

        }


//        System.out.println(JsonHelper.getInstance().getJson(projectNode));
//        JavaCoreAnalyzer javaCoreAnalyzer = new JavaCoreAnalyzer();
//        javaCoreAnalyzer.doAnalyzer(projectNode);
//        List<Node> nodeList = Search.getAllJavaClassNode(loader.getProjectNode());
//        Node node = new Node();
//        node.setChild(nodeList);
//        System.out.println(JsonHelper.getInstance().getJson(node));

        Graph<Node, DefaultEdge> graphTwo = new DefaultDirectedGraph<>(DefaultEdge.class);

        LoaderImpl loader_ = new LoaderImpl();
        loader_.load("D:\\DDP_DATA_TEST\\java-design-patterns-master\\java-design-patterns-master\\visitor\\src\\main\\java\\com\\iluwatar\\visitor");
        Node projectNode_ = loader_.getProjectNode();
        JavaCoreAnalyzer javaCoreAnalyzer_ = new JavaCoreAnalyzer();
        javaCoreAnalyzer_.doAnalyzer(projectNode_);

//        System.out.println(JsonHelper.getInstance().getJson(projectNode_));

        List<Node> nodes_ = Search.getAllJavaClassNode(projectNode_);
        Set<Dependency> dependencies_ = new HashSet<>();
        for (Node iNode : nodes_) {
            JavaClassNode javaClassNode = (JavaClassNode) iNode;
            dependencies_.addAll(javaClassNode.getDependencies());
        }

        Set<Node> nodeSet_ = new HashSet<>();
        for (Dependency dependency : dependencies_) {
            nodeSet_.add(dependency.getFrom());
            nodeSet_.add(dependency.getTo());
        }

        System.out.println("//====================//");
        for (Node node : nodeSet_) {
            graphTwo.addVertex(node);
            System.out.println("Vertex Caller: " + node.getName());
        }

        for (Dependency dependency : dependencies_) {
            graphTwo.addEdge(dependency.getFrom(), dependency.getTo());
            System.out.println("add EDGE: " + dependency.getFrom().getName() + " -- " + dependency.getTo().getName());
        }

        VF2SubgraphIsomorphismInspector inspector = new VF2SubgraphIsomorphismInspector(graphTwo, graphOne);
        System.out.println(inspector.isomorphismExists());
    }

    @Test
    public void algorithmGraph() {
        Graph<String, DefaultEdge> directedGraph =
                new DefaultDirectedGraph<>(DefaultEdge.class);
        directedGraph.addVertex("a");
        directedGraph.addVertex("b");
        directedGraph.addEdge("a", "b");


        Graph<String, DefaultEdge> defaultEdgeGraph2 = new DefaultDirectedGraph<>(DefaultEdge.class);
        defaultEdgeGraph2.addVertex("a");
        defaultEdgeGraph2.addVertex("b");
        defaultEdgeGraph2.addVertex("c");
        defaultEdgeGraph2.addEdge("a", "b");

        VF2SubgraphIsomorphismInspector inspector = new VF2SubgraphIsomorphismInspector(defaultEdgeGraph2, directedGraph);
        System.out.println(inspector.isomorphismExists());
    }


    @Test
    public void comparator() {
        DDPEdge edge = new DDPEdge();
        DDPEdge edge_ = new DDPEdge();

        Comparator<DDPEdge> edgeComparator = Comparator.comparingInt(o -> o.getDependencies().size());
        System.out.println(edgeComparator.compare(edge, edge_));
    }
}
