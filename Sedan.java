package oopsDesign;

//Sub class of Car type, gets derived in Car
public class Sedan extends Car{
	public Sedan(String license) {
		super(license,CarType.Sedan);
	}
}
