package factory;

public class Dog extends Animal {

    @Override
    public String makeSound() {
        return "Woof";
    }

    @Override
    public String getType() {
        return "Dog";
    }

    @Override
    public String getName() {
        return "Dog: Have a Name";
    }
}