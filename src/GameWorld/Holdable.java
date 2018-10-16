package GameWorld;

import GameWorld.GameWorld.Direction;
import GameWorld.GameColor;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
@XmlSeeAlso({KeyObject.class, KeyComponent.class})
//These allow the WorldObject to get the parameters of subclass using setters I think
//Don't get confused by the fact the default constructor is being used here

public abstract class Holdable extends WorldObject {

	public Holdable(int xPos, int yPos, int zPos, int xWidth, int yHeight, int zDepth, String Name, String Description, Direction d, GameColor gameColor){
    this.xPos=xPos;
    this.yPos=yPos;
    this.zPos=zPos;
    this.name=Name;
    this.description=Description;
    this.direction=d;
    this.gameColor = gameColor;
	}

	public Holdable() {}
}
