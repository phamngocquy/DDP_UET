package core.dom;


import com.github.javaparser.ast.Modifier;

import java.util.EnumSet;

public class JavaClassNode extends Node {
    private EnumSet<Modifier> modifiers;
    private Boolean isInterface;
    private Boolean isAbstract;

    public JavaClassNode() {
    }

    public EnumSet<Modifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(EnumSet<Modifier> modifiers) {
        this.modifiers = modifiers;
    }

    public Boolean getInterface() {
        return isInterface;
    }

    public void setInterface(Boolean anInterface) {
        isInterface = anInterface;
    }

    public Boolean getAbstract() {
        return isAbstract;
    }

    public void setAbstract(Boolean anAbstract) {
        isAbstract = anAbstract;
    }
}
