package factory;

public class Snake extends Animal {

    @Override
    public String makeSound() {
        return "Hiss";
    }

    @Override
    public String getType() {
        return "Snake";
    }
}