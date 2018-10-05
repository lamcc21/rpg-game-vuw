package GameWorld;

import java.io.IOException;
import java.util.List;

import GameWorld.GameWorld.Direction;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public abstract class Holdable extends WorldObject {

	public Holdable(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,String Name,String Description, Direction d, Color color) throws IOException {
		super(xPos,yPos,zPos,xWidth,yHeight,zDepth,Name,Description,d,color);
	}

	public Holdable() throws IOException {
        super();
    }

}
