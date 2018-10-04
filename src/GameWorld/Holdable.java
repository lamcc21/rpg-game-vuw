package GameWorld;

import java.awt.Color;
import java.util.List;

import GameWorld.GameWorld.Direction;

public abstract class Holdable extends WorldObject {

	public Holdable(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,String Name,String Description, Direction d) {
		super(xPos,yPos,zPos,xWidth,yHeight,zDepth,Name,Description,d);
	}

	@Override
	public List<WorldObject> getContents(){
		return null;
	}

}
