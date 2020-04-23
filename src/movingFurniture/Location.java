package movingFurniture;

public class Location {
	public final double x;
	public final double y;
	Location(double x, double y){
		this.x = x;
		this.y = y;
	}
	public double distanceTo(Location location) {
		double xD = Math.abs(x - location.x);
		double yD = Math.abs(y - location.y);
		double d = Math.sqrt(xD*xD + yD*yD);
		return d;
	}
	public String toString() {
		return "X: " + x + " Y: " + y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	
}
