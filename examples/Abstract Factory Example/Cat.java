package com.cakes.animals;

public class Cat extends Animal {

    @Override
    public String makeSound() {
        return "Meow";
    }

    public void getAnimal(Animal animal) {
        return animal.getName();
    }
}