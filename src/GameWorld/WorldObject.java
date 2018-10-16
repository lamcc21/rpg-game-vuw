package GameWorld;

import GameWorld.GameWorld.Direction;
import GameWorld.GameColor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
import java.util.Objects;

/**
 * An abstract class containing the basic properties of all Objects that are inside of
 * the rooms
 *
 * @author Francis Raureti
 *
 */
@XmlTransient
@XmlSeeAlso({KeyObject.class, KeyComponent.class, Container.class})
public abstract class WorldObject  {

	//these are the coordinates for the object, it would be good if we can
	//follow some sort of conventional approach:"facing north"

	protected int xPos;//the leftmost cube the object is  within
	protected int yPos;//the lowest cube the object is  within
	protected int zPos;//the closest cube the object is within

	protected Direction direction;

	protected String name;
	protected String description;
	//private final BufferedImage RoneItemone = ImageIO.read(getClass().getResource("black.png"));

	protected GameColor gameColor;
	protected List<WorldObject> contents;

	// All get() methods for World object, returns
	// new instance of required properties to avoid
	// unintended aliasing

	public WorldObject(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,
			String Name,String Description,Direction direction ){
		this.xPos=xPos;
		this.yPos=yPos;
		this.zPos=zPos;
		this.name=Name;
		this.description=Description;
		this.direction=direction;
	}

	/*
	 * separate constructor for making objects with gameColor value associated
	 */
	public WorldObject(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,String Name,
			String Description, Direction direction, GameColor gameColor){
		this.xPos=xPos;
		this.yPos=yPos;
		this.zPos=zPos;
		this.name=Name;
		this.description=Description;
		this.direction=direction;
		this.gameColor = gameColor;
	}

	public WorldObject(){}

	@XmlElement
	public int getX(){
		return xPos;
	}

	public void setX(int x) {
		this.xPos = x;
	}

	@XmlElement
	public int getY() {
		return yPos;
	}

	public void setY(int y) {
		this.yPos = y;
	}

	@XmlElement
	public int getZ() {
		return zPos;
	}

	public void setZ(int z) {
		this.zPos = z;
	}


	@XmlElement
	public String getName() {
		return name;
	}

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement
	public String getDescription() {
		return description;
	}

  public void setDescription(String description) {
    this.description = description;
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
	public GameColor getGameColor() {
		return gameColor;
	}

	public void setGameColor(GameColor c) {
		this.gameColor =c;
	}


	/**
	 * Auxillary method for determining left to right orientaion of objects for
	 * the renderer
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof WorldObject)) return false;
		WorldObject that = (WorldObject) o;
		return Objects.equals(getGameColor(), that.getGameColor());
	}

	public String getFilePath() {
		return "src/images/"+name+".png";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getGameColor());
	}

	public String toString() {
		return name;
	}
}
