package GameWorld;
import GameWorld.GameWorld.Direction;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class KeyObject extends Holdable {

	public KeyObject(int xPos, int yPos, int zPos, int xWidth, int yHeight, int zDepth, String Name, String Description, Direction d, GameColor gameColor){
    this.xPos=xPos;
    this.yPos=yPos;
    this.zPos=zPos;
    this.xWidth=xWidth;
    this.yHeight=yHeight;
    this.zWidth=zDepth;
    this.name=Name;
    this.description=Description;
    this.direction=d;
    this.gameColor = gameColor;
	}

	KeyObject(GameColor c){
		this.gameColor = c;
    this.description = "A "+ c.toString() +" key, I wonder what it opens..";
		this.name = c.toString()+" Key";
	}

	public KeyObject(){}
}
