package core.helper;

import core.constant.JavaNodeType;
import core.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class Search {

    public static List<Node> getAllJavaClassNode(Node projectNode) {
        List<Node> nodeList = doGetAllJavaClassNode(projectNode);
        return nodeList;
    }

    private static List<Node> doGetAllJavaClassNode(Node rootNode) {
        List<Node> nodeList = new ArrayList<Node>();
        if (rootNode.getNodeType().equals(JavaNodeType.JAVA_CLASS_NODE)) {
            nodeList.add(rootNode);
        } else {
            List<Node> children = rootNode.getChildren();
            for (Node node : children) {
                nodeList.addAll(doGetAllJavaClassNode(node));
            }
        }
        return nodeList;
    }

    public static List<Node> getAllJavaMethodNode(Node rootNode) {
        List<Node> result = doGetAllJavaClassNode(rootNode);
        return result;
    }

    private static List<Node> doGetAllJavaMethodNode(Node rootNode) {
        List<Node> nodeList = new ArrayList<Node>();
        if (rootNode.getNodeType().equals(JavaNodeType.JAVA_METHOD_NODE)) {
            nodeList.add(rootNode);
        } else {
            List<Node> children = rootNode.getChildren();
            for (Node node : children) {
                nodeList.addAll(doGetAllJavaMethodNode(node));
            }
        }
        return nodeList;
    }

}
