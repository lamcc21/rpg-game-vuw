package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Door {

	boolean isLocked;
	KeyObject key;

	public Door(boolean isLocked, KeyObject key) {
		this.isLocked=isLocked;
		this.key=key;
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
		this.key=key;
	}

}
