package core.helper;

import core.constant.JavaNodeType;
import core.dom.Node;
import core.helper.condition.ICondition;

import java.util.ArrayList;
import java.util.List;

public class Search {

    public static List<Node> getAllJavaClassNode(Node projectNode) {
        List<Node> nodeList = doGetAllJavaClassNode(projectNode);
        return nodeList;
    }

    /**
     *
     * @param classNode
     * @param iCondition
     * @return
     */
    public static List<Node> getAllJavaMethodNode(Node classNode,
                                                  ICondition iCondition) {
        List<Node> nodeList = new ArrayList<>();
        if (iCondition.isSatisfiable(classNode)) {
            for (Node iNode : classNode.getChildren()) {
                if (iNode.getNodeType().equals(JavaNodeType.JAVA_METHOD_NODE)) {
                    nodeList.add(iNode);
                }
            }
        }
        return nodeList;
    }

    /**
     *
     * @param rootNode
     * @return
     */
    private static List<Node> doGetAllJavaClassNode(Node rootNode) {
        List<Node> nodeList = new ArrayList<>();
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

    /**
     *
     * @param rootNode
     * @return
     */
    public static List<Node> getAllJavaMethodNode(Node rootNode) {
        List<Node> result = doGetAllJavaClassNode(rootNode);
        return result;
    }

    /**
     *
     * @param rootNode
     * @return
     */
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

    /**
     *
     * @param rootNode
     * @return
     */
    public static List<Node> getAllJavaFieldNode(Node rootNode) {
        List<Node> nodeList = new ArrayList<>();
        for (Node iNode : rootNode.getChildren()) {
            if (iNode.getNodeType().equals(JavaNodeType.JAVA_FIELD_NODE)) {
                nodeList.add(iNode);
            }
        }
        return nodeList;
    }

}
