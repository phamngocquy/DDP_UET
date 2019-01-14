package core.analyzer;

import core.dom.JavaClassNode;
import core.dom.Node;

import java.util.List;

public interface IAnalyzer {
    void doAnalyzer(Node projectNode);

    /**
     *
     * @param classNode
     * @param listJavaClassNode
     */
    void analyzerClassLevel(JavaClassNode classNode, List<Node> listJavaClassNode);

    /**
     *
     * @param classNode
     * @param listJavaClassNode
     */
    void analyzerMethodLevel(JavaClassNode classNode, List<Node> listJavaClassNode);

    /**
     *
     * @param classNode
     * @param listJavaClassNode
     */
    void analyzerFieldLevel(JavaClassNode classNode, List<Node> listJavaClassNode);

    /**
     *
     * @param classNode
     * @param typeValue
     * @param listJavaClassNode
     */
    void analyzerGenericType(JavaClassNode classNode, String typeValue, List<Node> listJavaClassNode);

}
