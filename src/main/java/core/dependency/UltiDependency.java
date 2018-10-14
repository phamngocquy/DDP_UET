package core.dependency;

import core.dom.JavaClassNode;
import core.dom.Node;

import java.util.HashSet;
import java.util.Set;

public class UltiDependency implements IDependency {
    private Node from;
    private Node to;
    private Set<Enum> types;


    public UltiDependency() {

    }

    @Override
    public void addDependency(Node caller, Node callee, Enum type) {

        // check dependency exists and Type to set
        for (UltiDependency dependency : caller.getDependencies()) {
            if (dependency.getFrom().getName().equals(caller.getName())
                    && dependency.getTo().getName().equals(callee.getName())) {
                dependency.addType(type);
                return;
            }
        }

        // if dependency not exists
        this.from = caller;
        this.to = callee;
        types = new HashSet<>();
        types.add(type);
        this.from.addDependencies(this);
        this.to.addDependencies(this);

    }


    @Override
    public String toString() {
        StringBuilder setOfDependencyType = new StringBuilder();
        for (Enum type : this.types) {
            setOfDependencyType.append("-").append(type.toString());
        }
        JavaClassNode from = (JavaClassNode) this.from;
        JavaClassNode to = (JavaClassNode) this.to;
        return String.format("From: %s-%s | To: %s-%s | Dependency: %s", from.getName(), from.getType(), to.getName(), to.getType(), setOfDependencyType.toString());
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

    public Set<Enum> getType() {
        return types;
    }

    public void addType(Enum type) {
        this.types.add(type);
    }

    public void setType(Set<Enum> type) {
        this.types = type;
    }
}
