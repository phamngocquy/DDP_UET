package core.analyzer;

import core.dom.Node;
import core.helper.Helper;
import core.helper.Search;

import java.io.Serializable;
import java.util.List;

public class JavaCoreAnalyzer {

    public JavaCoreAnalyzer() {
    }

    public void doAnalyzer(Node projectNode) {
        List<Node> listJavaClassFile = Search.getAllJavaClassNode(projectNode);


    }
}
