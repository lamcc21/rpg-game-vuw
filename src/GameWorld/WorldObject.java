package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * An abstract class containing the basic properties of all Objects that are inside of
 * the rooms
 *
 * @author Francis Raureti
 *
 */
@XmlRootElement
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

	@XmlElement
	public int getX(){
		return new Integer(xPos);
	}

	public void setX(int x) {
		this.xPos = x;
	}

	@XmlElement
	public int getY() {
		return new Integer(yPos);
	}

	public void setY(int y) {
		this.yPos = y;
	}

	@XmlElement
	public int getZ() {
		return new Integer(zPos);
	}

	public void setZ(int z) {
		this.zPos = z;
	}

	@XmlElement
	public int getWidth(){
		return new Integer(xWidth);
	}

	@XmlElement
	public int getHeight() {
		return new Integer(yHeight);
	}

	@XmlElement
	public int getDepth() {
		return new Integer(zWidth);
	}
	@XmlElement
	public String getName() {
		return new String(name);
	}
	/**
	 *
	 * @return description
	 */
	@XmlElement
	public String getDescription() {
		return new String(description);
	}


}
