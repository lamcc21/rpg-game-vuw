package GameWorld;

import GameWorld.GameWorld.Direction;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Wall {

	boolean isVisible;
	Direction direction;
	Door door;

	public Wall(boolean isVisible, Direction d) {
		this.isVisible=isVisible;
		this.direction=d;
	}

	public Wall() {}

	@XmlElement
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction d) {
		this.direction=d;
	}

	@XmlElement
	public boolean isVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean b) {
		isVisible=b;
	}

	public boolean hasDoor() {
		if(door!=null)return true;
		return false;
	}

}
