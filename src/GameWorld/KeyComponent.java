package GameWorld;

import GameWorld.GameWorld.Direction;
import GameWorld.GameColor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KeyComponent extends Holdable {

	public KeyComponent(int xPos, int yPos, int zPos, int xWidth, int yHeight, int zDepth, String Name, String Description, Direction d, GameColor gameColor){
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

	public KeyComponent(){
  }
}
