package core.dom;

public class JavaMethodNode extends JavaClassNode {

    public JavaMethodNode(Enum modifier) {
        super(modifier);
    }

    public JavaMethodNode(String name, String absolutePath, Enum modifier) {
        super(name, absolutePath, modifier);
    }
}
