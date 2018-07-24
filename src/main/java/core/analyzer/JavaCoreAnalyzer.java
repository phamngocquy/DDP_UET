package core.analyzer;

import core.dom.Node;
import core.helper.Search;
import core.util.JsonHelper;

import java.util.List;

public class JavaCoreAnalyzer {

    public JavaCoreAnalyzer() {
    }

    public void doAnalyzer(Node projectNode) {
        System.out.println(JsonHelper.getInstance().getJson(projectNode));
        List<Node> listJavaClassFile = Search.getAllJavaClassNode(projectNode);
//        System.out.println(JsonHelper.getInstance().getJson(listJavaClassFile.get(0)));
    }
}
