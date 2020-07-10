package oopsDesign;


//Car class for defining properties of cars 
public class Car {
	private String licence;
	CarType type;
	
	//Car constructor for defining license and type of cars.
	public Car(String license,CarType type) {
		this.licence = license;
		this.type = type;
	}
}
