package movingFurniture;

import java.util.ArrayList;

public class Room {
	private double width;
	private double length;
	private ArrayList<Furniture> furniture = new ArrayList<Furniture>();
	public ArrayList<Furniture> getFurniture(){
		return furniture;
	}
	public void addFurniture(Furniture furniture) {
		this.furniture.add(furniture);
	}
	public void addChair(double radius, double x, double y) {
		Furniture chair = new Chair(radius, x, y);
		furniture.add(chair);
	}
	public void addChair(double radius, Location location) {
		Furniture chair = new Chair(radius, location);
		furniture.add(chair);
	}
	public void addTable(double width, double length, double x, double y) {
		Furniture table = new Table(width, length, x, y);
		furniture.add(table);
	}
	public void addTable(double width, double length, Location location) {
		Furniture table = new Table(width, length, location);
		furniture.add(table);
	}
}
