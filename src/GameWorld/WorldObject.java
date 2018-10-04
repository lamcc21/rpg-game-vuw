package GameWorld;

import java.awt.Color;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import GameWorld.GameWorld.Direction;
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

	protected Direction direction;

	protected String name;
	protected String description;

	protected Color color;
	protected List<WorldObject> contents;

	// All get() methods for World object, returns
	// new instance of required properties to avoid
	// unintended aliasing

	public WorldObject(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,String Name,String Description,Direction direction ){
		this.xPos=xPos;
		this.yPos=yPos;
		this.zPos=zPos;
		this.xWidth=xWidth;
		this.yHeight=yHeight;
		this.zWidth=zDepth;
		this.name=Name;
		this.description=Description;
		this.direction=direction;
	}

	/*
	 * separate constructor for making objects with color value associated
	 */
	public WorldObject(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,String Name,
			String Description, Direction direction, Color color ){
		this.xPos=xPos;
		this.yPos=yPos;
		this.zPos=zPos;
		this.xWidth=xWidth;
		this.yHeight=yHeight;
		this.zWidth=zDepth;
		this.name=Name;
		this.description=Description;
		this.direction=direction;
		this.color=color;
	}

	public WorldObject() {}

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

	@XmlElement
	public Direction getDirection() {
		return this.direction;
	}

	public void setDirection(Direction d) {
		this.direction=d;
	}

	@XmlElement
	public List<WorldObject> getContents() {
		return this.contents;
	}

	public void setContents(List<WorldObject> contents) throws Exception {
		this.contents=contents;
	}

	@XmlElement
	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color=c;
	}


	/**
	 * returns distance from viewer depending on viewer perspective
	 * @param perspective
	 * @return
	 */
	public int getDistance(Direction perspective) {
		switch(perspective) {
		case NORTH:
			return zPos;
		case SOUTH:
			return Room.SIZE-zPos;
		case EAST:
			return Room.SIZE-xPos;
		case WEST:
			return xPos;
		}
		return 0;
	}

	/**
	 * Auxillary method for determining left to right orientaion of objects for
	 * the renderer
	 * @param Player perspective
	 * @return relative position along X axis
	 */
	public int getOrientation(Direction perspective) {
		switch(perspective) {
		case NORTH:
			return xPos;
		case SOUTH:
			return Room.SIZE-xPos;
		case EAST:
			return Room.SIZE-zPos;
		case WEST:
			return zPos;
		}
		return 0;
	}

}
