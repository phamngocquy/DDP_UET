package core.analyzer;

import core.constant.JavaPolymorphismType;
import core.constant.JavaTypeDependencies;
import core.dependency.Dependency;
import core.dom.JavaClassNode;
import core.dom.JavaFieldNode;
import core.dom.JavaMethodNode;
import core.dom.Node;
import core.helper.Helper;
import core.helper.Search;
import core.helper.condition.JavaClassNodeCondition;
import core.model.JavaParameter;
import core.model.Polymorphism;

import java.util.List;

public class JavaCoreAnalyzer {

    public JavaCoreAnalyzer() {
    }

    public void doAnalyzer(Node projectNode) {
        List<Node> listJavaClassNode = Search.getAllJavaClassNode(projectNode);

        // generate dependency
        for (Node node : listJavaClassNode) {
            JavaClassNode classNode = (JavaClassNode) node;

            analyzerClassLevel(classNode, listJavaClassNode);
            analyzerFieldLevel(classNode, listJavaClassNode);
            analyzerMethodLevel(classNode, listJavaClassNode);


        }
    }

    /**
     * X: class A extends class B
     * I: class A implements class B
     * F: class A has the field type of class B
     */
    private void analyzerClassLevel(JavaClassNode classNode, List<Node> listJavaClassNode) {
        for (Polymorphism polymorphism : classNode.getPolymorphismList()) {
            JavaClassNode iJavaNode = (JavaClassNode) getNodeByName(polymorphism.getModelName(), listJavaClassNode);
            if (iJavaNode != null) {

                if (polymorphism.getPolymorphismType() == JavaPolymorphismType.EXTENDS) {
                    new Dependency().addDependency(classNode, iJavaNode, JavaTypeDependencies.X);
                } else if (polymorphism.getPolymorphismType() == JavaPolymorphismType.IMPLEMENTS) {
                    new Dependency().addDependency(classNode, iJavaNode, JavaTypeDependencies.I);
                }
            }
        }
    }

    /**
     * MR: class A has a method with return type of class B
     */
    private void analyzerMethodLevel(JavaClassNode classNode, List<Node> listJavaClassNode) {
        List<Node> methodNodes = Search.getAllJavaMethodNode(classNode, new JavaClassNodeCondition());
        for (Node iNode : methodNodes) {
            JavaMethodNode node = (JavaMethodNode) iNode;

            // return type:
            String returnType = Helper.extracType(node.getReturnType());
            if (!returnType.equals(classNode.getName())) {
                JavaClassNode iClassNode = (JavaClassNode) getNodeByName(returnType, listJavaClassNode);
                if (iClassNode != null) {
                    new Dependency().addDependency(classNode, iClassNode, JavaTypeDependencies.MR);
                }

            }

            // parameter
            List<JavaParameter> parameterList = node.getParameterList();
            for (JavaParameter parameter : parameterList) {
                JavaClassNode iClassNode = (JavaClassNode) getNodeByName(parameter.getValueType(), listJavaClassNode);
                if (iClassNode != null) {
                    new Dependency().addDependency(classNode, iClassNode, JavaTypeDependencies.MI);

                }
            }
        }
    }

    private void analyzerFieldLevel(JavaClassNode classNode, List<Node> listJavaClassNode) {
        List<Node> javaFieldNodeList = Search.getAllJavaFieldNode(classNode);
        for (Node iNode : javaFieldNodeList) {
            JavaFieldNode node = (JavaFieldNode) iNode;
            JavaClassNode iClassNode = (JavaClassNode) getNodeByName(node.getValueType(), listJavaClassNode);
            if (iClassNode != null) {
                new Dependency().addDependency(classNode, iClassNode, JavaTypeDependencies.F);
            }
        }

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
