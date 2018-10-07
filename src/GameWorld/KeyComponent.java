package GameWorld;

import GameWorld.GameWorld.Direction;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

@XmlRootElement
public class KeyComponent extends Holdable {

	public KeyComponent(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,String Name,String Description, Direction d,Color color) throws IOException {
    this.xPos=xPos;
    this.yPos=yPos;
    this.zPos=zPos;
    this.xWidth=xWidth;
    this.yHeight=yHeight;
    this.zWidth=zDepth;
    this.name=Name;
    this.description=Description;
    this.direction=d;
    this.color=color;
	}

	public KeyComponent() throws IOException {
  }
}
