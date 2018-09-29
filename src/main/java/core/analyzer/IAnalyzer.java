package core.analyzer;

import core.dom.JavaClassNode;
import core.dom.Node;

import java.util.List;

public interface IAnalyzer {
    void doAnalyzer(Node projectNode);

    void analyzerClassLevel(JavaClassNode classNode, List<Node> listJavaClassNode);

    void analyzerMethodLevel(JavaClassNode classNode, List<Node> listJavaClassNode);

    void analyzerFieldLevel(JavaClassNode classNode, List<Node> listJavaClassNode);

    void analyzerGenericType(JavaClassNode classNode, String typeValue, List<Node> listJavaClassNode);

}
