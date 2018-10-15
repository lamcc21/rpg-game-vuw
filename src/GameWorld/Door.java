package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Door {

	private boolean isLocked;
	private GameColor gameColor;

	public Door(boolean isLocked, GameColor gameColor) {
		this.isLocked=isLocked;
		this.gameColor = gameColor;
	}

	public Door(boolean isLocked) {
		this.isLocked=isLocked;
	}

	public Door() {}

	@XmlElement
	public boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean b) {
		isLocked=b;
	}

	@XmlElement
	public GameColor getGameColor() {
		return gameColor;
	}

	public void setGameColor(GameColor c) {
		this.gameColor =c;
	}


}
