package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class KeyObject extends Holdable {

	public KeyObject(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,String Name,String Description) {
		super(xPos,yPos,zPos,xWidth,yHeight,zDepth,Name,Description);
	}



}
