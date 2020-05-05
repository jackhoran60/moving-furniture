package movingFurniture;

public abstract class Collision {
	
	public static boolean touching(MovingFObject one, MovingFObject two) {
		//returns true if 2 objects are touching
		if(one.equals(two))
			return false;
		double xD = one.getLocation().x < two.getLocation().x ? two.getLocation().x - one.getLocation().x - one.width : one.getLocation().x - two.getLocation().x - two.width;
		double yD = one.getLocation().y < two.getLocation().y ? two.getLocation().y - one.getLocation().y - one.length : one.getLocation().y - two.getLocation().y - two.length;
		if(xD <= 0 && yD <= 0)
			return true;
		return false;
	}
	public static boolean touchingWall(MovingFObject obj, double sWidth, double sHeight) {
		//sWidth and sHeight are width and height of scenario
		if(obj.getLocation().x <= 0) 
			return true;
		if(obj.getLocation().x + obj.width >= sWidth)
			return true;
		if(obj.getLocation().y <= 0)
			return true;
		if(obj.getLocation().y + obj.length >= sHeight)
			return true;
		return false;
	}
	public static double getXDist(MovingFObject one, MovingFObject two) {
		//a negative return value denotes how far overlapping the two objects are
		return one.getLocation().x < two.getLocation().x ? two.getLocation().x - one.getLocation().x - one.width : one.getLocation().x - two.getLocation().x - two.width;
	}
	public static double getYDist(MovingFObject one, MovingFObject two) {
		//a negative return value denotes how far overlapping the two objects are
		return one.getLocation().y < two.getLocation().y ? two.getLocation().y - one.getLocation().y - one.length : one.getLocation().y - two.getLocation().y - two.length;
	}
	public static double getWallDist(MovingFObject obj, int wall, double sWidth, double sHeight) {
		//0 is left wall, 1 is bottom wall, 2 is left wall, 3 is top wall
		if(wall == 0) {
			return sWidth - obj.getLocation().x - obj.width;
		}
		if(wall == 1) {
			return sHeight - obj.getLocation().y - obj.length;
		}
		if(wall == 2) {
			return obj.getLocation().x;
		}
		if(wall == 3) {
			return obj.getLocation().y;
		}
		return 0;
	}
}
