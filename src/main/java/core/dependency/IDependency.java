package core.dependency;

import core.dom.Node;

public interface IDependency {
    void addDependency(Node caller, Node callee, Enum type);
}
