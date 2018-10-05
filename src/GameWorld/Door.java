package GameWorld;

import java.awt.Color;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Door {

	boolean isLocked;
	Color color;

	public Door(boolean isLocked, Color color) {
		this.isLocked=isLocked;
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
	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color=c;
	}


}
