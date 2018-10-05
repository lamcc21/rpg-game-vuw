package GameWorld;

import GameWorld.GameWorld.Direction;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.IOException;

@XmlTransient
@XmlSeeAlso({KeyObject.class, KeyComponent.class})
public abstract class Holdable extends WorldObject {

	public Holdable(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,String Name,String Description, Direction d, Color color) throws IOException {
    this.xPos=xPos;
    this.yPos=yPos;
    this.zPos=zPos;
    this.xWidth=xWidth;
    this.yHeight=yHeight;
    this.zWidth=zDepth;
    this.name=Name;
    this.description=Description;
    this.direction=direction;
    this.color=color;
	}

	public Holdable() throws IOException {}

}
