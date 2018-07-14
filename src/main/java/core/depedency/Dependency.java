package core.depedency;

import core.dom.Node;

public class Dependency {
    private Node from;
    private Node to;
    private Enum type;

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
