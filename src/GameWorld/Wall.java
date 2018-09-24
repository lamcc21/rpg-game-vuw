package GameWorld;

import GameWorld.GameWorld.Direction;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Wall {

	boolean isVisible;
	Direction direction;

	public Wall(boolean isVisible, Direction d) {
		this.isVisible=isVisible;
		this.direction=d;
	}
	@XmlElement
	public Direction getDirection() {
		return direction;
	}

	@XmlElement
	public boolean isVisible() {
		return isVisible;
	}

}
