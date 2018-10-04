package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import GameWorld.GameWorld.Direction;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Container extends WorldObject {



	public Container(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,
	String Name,String Description,Direction direction, List<WorldObject> contents ) {
		super(xPos,yPos,zPos,xWidth,yHeight,zDepth,Name,Description,direction);
		try {
			this.setContents(contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Container() {}


	public boolean canContain(WorldObject ob) {
		return(this.xWidth>ob.xWidth && this.yHeight>ob.yHeight && this.zWidth>ob.zWidth);
	}

	/**
	 * adds object to Container
	 * @param ob
	 */
	public void addWorldObject(WorldObject ob) {
		if(canContain(ob))contents.add(ob);
	}



}
