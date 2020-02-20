package movingFurniture;

public class Location {
	private int x;
	private int y;
	Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getDistance(Location otherLoc) {
		double xDist = Math.pow(otherLoc.getX()-x, 2);
		double yDist = Math.pow(otherLoc.getY()-y, 2);
		int dist = (int) Math.sqrt(xDist+yDist);
		return dist;
	}
}
