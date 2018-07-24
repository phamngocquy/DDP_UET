package core.dom;

import com.github.javaparser.ast.Modifier;
import core.constant.JavaNodeType;

import java.util.EnumSet;

public class JavaFieldNode extends Node {
    private String valueType;
    private EnumSet<Modifier> modifiers;

    public JavaFieldNode() {
        super.nodeType = JavaNodeType.JAVA_FIELD_NODE;
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
