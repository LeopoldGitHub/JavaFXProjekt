import java.util.concurrent.atomic.AtomicInteger;

public class Vehicle {
	private static final AtomicInteger count = new AtomicInteger(1);
	protected String brand,type;
	protected int horsepower,id;
	
	public Vehicle(String brand, String type, int horsepower) {
		this.brand = brand;
		this.type = type;
		this.id = count.getAndIncrement();
		this.horsepower = horsepower;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	
	public static AtomicInteger getCount() {
		return count;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getHorsepower() {
		return horsepower;
	}
	
	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %6d, Brand: %13s, Model: %10s, Horsepower: %4d",id,brand,type,horsepower);
	}
}
