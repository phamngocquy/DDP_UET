package core.dependency;

import core.constant.JavaTypeDependencies;
import core.dom.Node;

public interface IDependency {
    void addDependency(Node caller, Node callee, JavaTypeDependencies type);
}
