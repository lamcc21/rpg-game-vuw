package GameWorld;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import GameWorld.GameWorld.Direction;

@XmlRootElement
public class KeyComponent extends Holdable {

	public KeyComponent(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,String Name,String Description, Direction d,Color color) throws IOException {
		super(xPos,yPos,zPos,xWidth,yHeight,zDepth,Name,Description,d,color);
	}

	public KeyComponent() throws IOException {
		super();
	}


}
