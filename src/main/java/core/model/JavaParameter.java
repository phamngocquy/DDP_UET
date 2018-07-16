package core.model;

public class JavaParameter {
    private String name;
    private Enum value_type;

    public JavaParameter() {
    }

    public JavaParameter(String name, Enum value_type) {
        this.name = name;
        this.value_type = value_type;
    }
}
