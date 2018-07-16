package core.dom;

import core.model.JavaParameter;

import java.util.List;

public class JavaMethodNode extends JavaClassNode {

    private String returnType;

    private List<JavaParameter> parameterList;

    public JavaMethodNode() {
    }

    public JavaMethodNode(String name, String absolutePath, Enum modifier, String returnType, List<JavaParameter> parameterList) {
        super(name, absolutePath, modifier);
        this.returnType = returnType;
        this.parameterList = parameterList;
    }
}
