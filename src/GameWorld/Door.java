package GameWorld;

import java.awt.Color;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Door {

	boolean isLocked;
	KeyObject key;
	Color color;

	public Door(boolean isLocked, KeyObject key, Color color) {
		this.isLocked=isLocked;
		this.key=key;
		this.color=color;
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
	public KeyObject getKey() {
		return key;
	}

	public void setKey(KeyObject k) {
		this.key=k;
	}

	@XmlElement
	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color=c;
	}
}
