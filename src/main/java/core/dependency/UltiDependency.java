package core.dependency;

import core.dom.JavaClassNode;
import core.dom.Node;

import java.util.HashSet;
import java.util.Set;

public class UltiDependency implements IDependency {
    private Node from;
    private Node to;
    private Set<Enum> type;


    public UltiDependency(Node from, Node to, Enum type) {
        this.from = from;
        this.to = to;
        this.type = new HashSet<>();
        this.type.add(type);
    }


    @Override
    public void addDependency(Node caller, Node callee, Enum type) {
        if (caller.getName().equals(this.from.getName()) &&
                callee.getName().equals(this.to.getName())) {
            if (this.type == null) this.type = new HashSet<>();
            this.type.add(type);
        }

    }


    @Override
    public String toString() {
        StringBuilder setOfDependencyType = new StringBuilder();
        for (Enum type : this.type) {
            setOfDependencyType.append("-").append(type.toString());
        }
        JavaClassNode from = (JavaClassNode) this.from;
        JavaClassNode to = (JavaClassNode) this.to;
        return String.format("From: %s-%s | To: %s-%s | Dependency: %s", from.getName(), from.getType(), to.getName(), to.getType(), setOfDependencyType.toString());
    }
}
