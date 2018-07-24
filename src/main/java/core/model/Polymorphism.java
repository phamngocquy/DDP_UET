package core.model;

public class Polymorphism {
    private String modelName;
    private Enum polymorphismType;


    public Polymorphism() {
    }

    public Polymorphism(String modelName, Enum polymorphismType) {
        this.modelName = modelName;
        this.polymorphismType = polymorphismType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Enum getPolymorphismType() {
        return polymorphismType;
    }

    public void setPolymorphismType(Enum polymorphismType) {
        this.polymorphismType = polymorphismType;
    }
}
