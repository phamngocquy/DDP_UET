package core.dom;

public class JavaClassNode extends Node {
    private Enum modifier;

    public JavaClassNode() {
    }

    public JavaClassNode(String name, String absolutePath, Enum modifier) {
        super(name, absolutePath);
        this.modifier = modifier;
    }

    public Enum getModifier() {
        return modifier;
    }

    public void setModifier(Enum modifier) {
        this.modifier = modifier;
    }
}
