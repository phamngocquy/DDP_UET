package core.analyzer;

import core.constant.JavaPolymorphismType;
import core.constant.JavaTypeDependencies;
import core.dependency.Dependency;
import core.dom.JavaClassNode;
import core.dom.Node;
import core.helper.Search;
import core.model.Polymorphism;

import java.util.List;

public class JavaCoreAnalyzer {

    public JavaCoreAnalyzer() {
    }

    public void doAnalyzer(Node projectNode) {
        List<Node> listJavaClassFile = Search.getAllJavaClassNode(projectNode);

        // generate dependency


        for (Node node : listJavaClassFile) {
            JavaClassNode classNode = (JavaClassNode) node;
            for (Polymorphism polymorphism : classNode.getPolymorphismList()) {
                JavaClassNode iJavaNode = (JavaClassNode) getNodeByName(polymorphism.getModelName(), listJavaClassFile);
                if (iJavaNode != null) {

                    if (polymorphism.getPolymorphismType() == JavaPolymorphismType.EXTENDS) {

                        new Dependency().addDependency(classNode, iJavaNode, JavaTypeDependencies.X);

                    } else if (polymorphism.getPolymorphismType() == JavaPolymorphismType.IMPLEMENTS) {

                        new Dependency().addDependency(classNode, iJavaNode, JavaTypeDependencies.I);
                    }
                }
            }
        }
    }

    /**
     * X: class A extends class B
     * I: class A implements class B
     * F: class A has the field type of class B
     */
    private void analyzerClassLevel() {

    }

    /**
     * MR: class A has a method with return type of class B
     */
    private void analyzerMethodLevel() {

    }


    private Node getNodeByName(String name, List<Node> listNode) {
        for (Node node : listNode) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

}
