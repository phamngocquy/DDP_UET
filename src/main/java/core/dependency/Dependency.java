package core.dependency;

import core.dom.Node;

public class Dependency {
    private Node from;
    private Node to;
    private Enum type;


    public void addDependency(Node caller, Node callee) {
        /**/
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
        return String.format("From: %s | To: %s | Type: %s",
                from.getName(), to.getName(), String.valueOf(type));
    }
}
