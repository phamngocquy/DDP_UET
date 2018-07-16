package core.dom;

public class JavaFieldNode extends JavaClassNode {
    private Enum modifier;
    private String value_type;
    private String name;

    public JavaFieldNode(Enum modifier) {
        super(modifier);
    }

}
