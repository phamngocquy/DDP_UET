package factory;

public abstract class Animal {
    public abstract String makeSound();

    public String getType() {
        return "Animal";
    }
    public String getName(){
        return "Animal: No Name";
    }
}