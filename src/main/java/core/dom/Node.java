package core.dom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import core.dependency.Dependency;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {

    private int id;
    private String name;
    private String absolutePath;
    String nodeType;
    private Enum type;

    @JsonIgnore
    private Node paren;
    private List<Node> children;

    private List<Dependency> dependencies;

    public Node() {
        this.children = new ArrayList<Node>();
        this.dependencies = new ArrayList<Dependency>();
        nodeType = "Node";
    }

    public Node(String name, String absolutePath) {
        this.name = name;
        this.absolutePath = absolutePath;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;

    }

    public void addDependencies(Dependency dependency) {
        if (dependency == null) System.err.println("[ADD DEPENDENCY]: dependency is null");
        this.dependencies.add(dependency);
    }

    public void setChild(List<Node> listChild) {
        this.children = listChild;
    }


    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void addChild(Node node) {
        if (node == null) System.err.println("[ADD CHILD]: node is null");
        this.children.add(node);
    }

    @JsonIgnore
    public List<Node> getChild() {
        return children;
    }

    public int getId() {
        return id;
    }

    @JsonIgnore
    public List<Node> getAllChild() {
        return doGetAllChild(this);
    }

    private List<Node> doGetAllChild(Node parentNode) {
        List<Node> allChild = new ArrayList<Node>();
        for (Node iNode : parentNode.getChild()) {
            allChild.add(iNode);
            allChild.addAll(doGetAllChild(iNode));
        }
        return allChild;
    }


    public void setParent(Node node) {
        this.paren = node;
    }

    @JsonIgnore
    public Node getParent() {
        return paren;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
