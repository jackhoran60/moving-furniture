package movingFurniture;

public abstract class MovingFObject extends Movable {
	public final Start start;
	public final double width;
	public final double length;
	public final double maxVelocity;
	public final Goal goal;
	public MovingFObject(double width, double length, double maxVelocity, Start start, Goal goal) {
		super(start);
		this.width = width;
		this.length = length;
		this.maxVelocity = maxVelocity;
		this.start = start;
		this.goal = goal;

	}
	
	public boolean canMoveTo(Location newLoc) {
		return getLocation().distanceTo(newLoc) <= maxVelocity;
			
	}
	public int distanceToObject(MovingFObject object) {
		//returns the distance between this object and another in parameter
		double xDist = Math.abs(getLocation().x - object.getLocation().x) - (width / 2) - (object.width / 2);
		double yDist = Math.abs(getLocation().x - object.getLocation().x) - (width / 2) - (object.width / 2);
		int dist = (int) (Math.pow(xDist, 2) + Math.pow(yDist, 2));
		return dist;
	}
	public boolean isTouchingObject(MovingFObject object) {
		//returns true if this object is touching object in parameter
		if(this.equals(object))
			return false;
		double xDist = Math.abs(getLocation().x - object.getLocation().x) - (width / 2) - (object.width / 2);
		if(xDist <= 0 && Math.abs(object.getLocation().y - getLocation().y) < length / 2 + object.length / 2)
			return true;
		double yDist = Math.abs(getLocation().y - object.getLocation().y) - (length / 2) - (object.length / 2);
		if(yDist <= 0 && Math.abs(object.getLocation().y - getLocation().y) < width / 2 + object.width / 2)
			return true;
		return false;
	}
	public boolean locationTouchingLocation(int locType, MovingFObject object, int objLocType) {
		if(this.equals(object))
			return false;
		
		Location temp;
		Location objTemp;
		if(locType == 0)
			temp = start;
		else if(locType == 1)
			temp = getLocation();
		else
			temp = goal;
		if(objLocType == 0)
			objTemp = object.start;
		else if(objLocType == 1)
			objTemp = object.getLocation();
		else
			objTemp = object.goal;
		
		double xDist = Math.abs(temp.x - objTemp.x) - (width / 2) - (object.width / 2);
		if(xDist <= 0 && Math.abs(temp.y - objTemp.y) < length / 2 + object.length / 2)
			return true;
		double yDist = Math.abs(temp.y - objTemp.y) - (length / 2) - (object.length / 2);
		if(yDist <= 0 && Math.abs(objTemp.x - temp.x) < width / 2 + object.width / 2)
			return true;
		return false;
		
	}
//	public String directionToObject(MovingObject object) {
//		//return closest direction between object
//		//returns "Left", "Right", "Up", or "Down"
//		String horizontal = "Left";
//		String vertical = "Up"; 
//		double xLen = object.getLocation().getX() - location.getX();
//		if(xLen > 0) {
//			horizontal = "Right";
//		}
//		double yLen = object.getLocation().getY() - location.getY();
//		if(yLen > 0) {
//			vertical = "Down";
//		}
//		if(Math.abs(xLen) > Math.abs(yLen)) {
//			return vertical;
//		}
//		return horizontal;
//	}
//	public int distanceFromEdge(Location location) {
//		double xDist = (Math.abs(location.getX()-this.location.getX()) - width) / 2;
//		double yDist = (Math.abs(location.getY()-this.location.getY()) - width) / 2;
//		int dist = (int) (Math.pow(xDist, 2) + Math.pow(yDist, 2));
//		return dist;
//	}
//	public boolean isTouchingLocation(Location location) {
//		if(Math.abs(location.getX() - this.location.getX()) < width / 2) {
//			return false;	
//		}
//		if(Math.abs(location.getY() - this.location.getY()) < length / 2) {
//			return false;	
//		}
//		return true;
//	}
//	public boolean moveLeft(Room room) {
//		if(location.getX()-this.width/2 > 0) {
//			ArrayList<MovingObject> o = room.getObjects();
//			location.setX(location.getX()-1);
//			for(int i = 0; i < room.getObjects().size(); i++) {
//				MovingObject mo = room.getObjects().get(i);
//				if(isTouchingObject(mo)) {
//					location.setX(location.getX()+1);
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
//	public boolean moveRight(Room room) {
//		if(location.getX()+this.width/2 < room.getWidth()) {
//			ArrayList<MovingObject> o = room.getObjects();
//			location.setX(location.getX()+1);
//			for(int i = 0; i < o.size(); i++) {
//				MovingObject mo = o.get(i);
//				if(isTouchingObject(mo)) {
//					location.setX(location.getX()-1);
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
//	public boolean moveUp(Room room) {
//		if(location.getY()-this.length/2 > 0) {
//			ArrayList<MovingObject> o = room.getObjects();
//			location.setY(location.getY()-1);
//			for(int i = 0; i < o.size(); i++) {
//				MovingObject mo = o.get(i);
//				if(isTouchingObject(mo)) {
//					location.setY(location.getY()+1);
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
//	public boolean moveDown(Room room) {
//		if(location.getY()+this.length/2 < room.getHeight()) {
//			ArrayList<MovingObject> o = room.getObjects();
//			location.setY(location.getY()+1);
//			for(int i = 0; i < o.size(); i++) {
//				MovingObject mo = o.get(i);
//				if(isTouchingObject(mo)) {
//					location.setY(location.getY()-1);
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
	public String toString() {
		return "Current: X: " + getLocation().x + ", Y: " + getLocation().y + " Goal: X: " + goal.x + ", Y: " + goal.y;
	}
}
