package core.dom;

import core.depedency.Dependency;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int id;
    private String name;
    private String absolutePath;
    private List<Node> children;
    private Node paren;
    private Enum type;


    private List<Dependency> dependencies;

    public Node() {
        this.children = new ArrayList<Node>();
        this.dependencies = new ArrayList<Dependency>();
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

    public void addChild(Node node) {
        if (node == null) System.err.println("[ADD CHILD]: node is null");
        this.children.add(node);
    }

    public List<Node> getChild() {
        return children;
    }

    public int getId() {
        return id;
    }

    public List<Node> getAllChild() {
        List<Node> allChild = new ArrayList<Node>();
        for (Node iNode : this.children) {
            allChild.add(iNode);
            allChild.addAll(getAllChild());
        }
        return allChild;
    }


    public void setParent(Node node) {
        this.paren = node;
    }

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
}
