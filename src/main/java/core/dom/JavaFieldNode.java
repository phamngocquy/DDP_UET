package core.dom;

import com.github.javaparser.ast.Modifier;

import java.util.EnumSet;

public class JavaFieldNode extends Node {
    private String valueType;
    private EnumSet<Modifier> modifiers;

    public JavaFieldNode() {
        super.nodeType = "JavaFieldNode";
    }

    public String getValueType() {
        return valueType;
    }

    public void setValue_type(String valueType) {
        this.valueType = valueType;
    }

    public EnumSet<Modifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(EnumSet<Modifier> modifiers) {
        this.modifiers = modifiers;
    }
}
