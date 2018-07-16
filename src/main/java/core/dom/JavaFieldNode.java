package core.dom;

public class JavaFieldNode extends JavaClassNode {
    private String value_type;


    public JavaFieldNode(String name, String absolutePath, Enum modifier, String value_type) {
        super(name, absolutePath, modifier);
        this.value_type = value_type;
    }
}
