package core.helper.condition;

import core.dom.JavaClassNode;
import core.dom.Node;

public class JavaClassNodeCondition implements ICondition {
    @Override
    public boolean isSatisfiable(Node node) {
        return (node instanceof JavaClassNode);
    }
}
