package core.model;

import java.io.Serializable;

public class JavaParameter implements Serializable {
    private String name;
    private String valueType;

    public JavaParameter() {
    }

    public JavaParameter(String name, String valueType) {
        this.name = name;
        this.valueType = valueType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
