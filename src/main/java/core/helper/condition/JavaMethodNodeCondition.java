package core.helper.condition;

import core.dom.JavaMethodNode;
import core.dom.Node;

public class JavaMethodNodeCondition implements ICondition {
    @Override
    public boolean isSatisfiable(Node node) {
        return (node instanceof JavaMethodNode);
    }
}
