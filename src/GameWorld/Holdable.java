package GameWorld;

import java.awt.Color;
import java.util.List;

import GameWorld.GameWorld.Direction;

public abstract class Holdable extends WorldObject {

	public Holdable(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,String Name,String Description, Direction d, Color color) {
		super(xPos,yPos,zPos,xWidth,yHeight,zDepth,Name,Description,d,color);
	}

	@Override
	public List<WorldObject> getContents(){
		return null;
	}

	@Override
	public void setContents(List<WorldObject> contents) throws Exception{
		throw new Exception("Object type cannot contain other elements");
	}

}
