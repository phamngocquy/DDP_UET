package core.dom;


import com.github.javaparser.ast.Modifier;
import core.constant.JavaNodeType;
import core.model.Polymorphism;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class JavaClassNode extends Node {
    private EnumSet<Modifier> modifiers;
    private Boolean isInterface;
    private Boolean isAbstract;
    private List<Polymorphism> polymorphismList;
    private Enum type;


    public JavaClassNode() {
        super.nodeType = JavaNodeType.JAVA_CLASS_NODE;
        this.polymorphismList = new ArrayList<Polymorphism>();
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

    public List<Polymorphism> getPolymorphismList() {
        return polymorphismList;
    }

    public void setPolymorphismList(List<Polymorphism> polymorphismList) {
        this.polymorphismList = polymorphismList;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }
}
