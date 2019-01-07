package factory;

public class Tyrannosaurus extends Animal {

    @Override
    public String makeSound() {
        return "Roar";
    }

    @Override
    public String getType() {
        return "Tyrannosaurus";
    }
}