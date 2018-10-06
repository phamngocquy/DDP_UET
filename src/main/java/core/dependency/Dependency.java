package core.dependency;

import core.dom.JavaClassNode;
import core.dom.Node;

public class Dependency implements IDependency {
    private Node from;
    private Node to;
    private Enum type;

    public Dependency() {
    }

    public void addDependency(Node caller, Node callee, Enum type) {
        /**/
        this.type = type;
        this.from = caller;
        this.to = callee;

        if (from != null && to != null && !from.equals(to)
                && !from.getDependencies().contains(this) && !to.getDependencies().contains(this)) {
            this.from.addDependencies(this);
            this.to.addDependencies(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        Dependency that = (Dependency) obj;
        return this.getFrom().equals(that.getFrom()) && this.getTo().equals(that.getTo());
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        JavaClassNode node_from = (JavaClassNode) from;
        JavaClassNode node_to = (JavaClassNode) to;
        return String.format("From: %s-%s | To: %s-%s | Type: %s",
                from.getName(), node_from.getType(), to.getName(), node_to.getType(), String.valueOf(type));
    }
}
