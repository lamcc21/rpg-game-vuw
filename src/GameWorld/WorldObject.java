package GameWorld;

/**
 * An abstract class containing the basic properties of all Objects that are inside of
 * the rooms
 * 
 * @author Francis Raureti
 *
 */
public abstract class WorldObject {
	
	//these are the coordinates for the object, it would be good if we can 
	//follow some sort of conventional approach:"facing north"
	
	protected int xPos;//the leftmost cube the object is  within
	protected int yPos;//the lowest cube the object is  within
	protected int zPos;//the closest cube the object is within
	
	//these are the dimensions of the object. Currently they are integers but 
	//if this impacts the drawing then we can change to doubles
	protected int xWidth;
	protected int yHeight;
	protected int zWidth;
	
	protected String name;
	protected String description;
	
	
	// All get() methods for World object, returns  
	// new instance of required properties to avoid 
	// unintended aliasing
	
	
	public int getX(){
		return new Integer(xPos);
	}
	
	public int getY() {
		return new Integer(yPos);
	}
	
	public int getZ() {
		return new Integer(zPos);
	}
	
	public int getWidth(){
		return new Integer(xWidth);
	}
	
	public int getHeight() {
		return new Integer(yHeight);
	}
	
	public int getDepth() {
		return new Integer(zWidth);
	}
	
	public String getName() {
		return new String(name);
	}
	/**
	 *  
	 * @return description 
	 */
	public String getDescription() {
		return new String(description);
	}
	
	
}
