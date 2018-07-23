package core.dom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.Parameter;
import core.model.JavaParameter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class JavaMethodNode extends Node {

    private String returnType;
    private EnumSet<Modifier> modifiers;


    private List<JavaParameter> parameterList;

    public JavaMethodNode() {
        parameterList = new ArrayList<JavaParameter>();
        this.nodeType = "JavaMethodNode";
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<JavaParameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<JavaParameter> parameterList) {
        this.parameterList = parameterList;
    }


    public EnumSet<Modifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(EnumSet<Modifier> modifiers) {
        this.modifiers = modifiers;
    }
}
