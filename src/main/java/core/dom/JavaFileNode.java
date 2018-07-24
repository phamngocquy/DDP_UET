package core.dom;

import core.constant.JavaNodeType;

public class JavaFileNode extends Node {
    public JavaFileNode() {
        super.nodeType = JavaNodeType.JAVA_FILE_NODE;
    }
}
