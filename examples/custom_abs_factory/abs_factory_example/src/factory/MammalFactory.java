package factory;


public class MammalFactory extends SpeciesFactory {

	@Override
	public Animal getAnimal(String type) {
		if ("dog".equals(type)) {
			return new Dog();
		} else {
			return new Cat();
		}
	}

}