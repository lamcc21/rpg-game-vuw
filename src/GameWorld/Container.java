package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import GameWorld.GameWorld.Direction;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Container extends WorldObject {
  private boolean isOpen;
  private boolean isSelected;

	public Container(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,
	String Name,String Description,Direction direction, List<WorldObject> contents ) throws IOException {
		super(xPos,yPos,zPos,xWidth,yHeight,zDepth,Name,Description,direction);
    this.isOpen = false;
		try {
			this.setContents(contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Container(int xPos,int yPos, int zPos, int xWidth, int yHeight, int zDepth,
	String Name,String Description,Direction direction) throws IOException {
		super(xPos,yPos,zPos,xWidth,yHeight,zDepth,Name,Description,direction);
    this.isOpen = false;
		try {
			this.setContents(new ArrayList<WorldObject>());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Container()throws IOException {
	  this.isOpen = false;
  }

	public void OpenorClose(){
    isOpen = !isOpen;
  }

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
	
	public void setIsSelected(Boolean b) {
		isSelected =b;
	}
	
	public boolean getIsSelected() {
		return isSelected;
	}





}
