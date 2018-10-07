package GameWorld;



import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import GameWorld.GameWorld.Direction;

/**
 * Player class
 * will keep track of most information relative to player
 * constructor currently only requires room as default inventory will be empty
 * and direction can start facing north
 * @author rauretfran
 *
 */

@XmlRootElement
public class Player {

	private Direction perspective;
	private List<WorldObject> inventory;
	private int xPos;
	private int yPos;

	public Player(int xpos,int ypos) {
	  this.perspective=Direction.NORTH;
	  this.inventory = new ArrayList<WorldObject>();
	  this.xPos=xpos;
	  this.yPos=ypos;
	}
//
	public Player() {}

	/**
	 * all getters and setters for fields in class
	 *
	 */

	@XmlElement
	public Direction getPerspective() {
		return perspective;
	}

	public void setPerspective(Direction newPer) {
		this.perspective=newPer;
	}

	@XmlElement
	public List<WorldObject> getInventory(){
		return inventory;
	}

	public void setInventory(List<WorldObject> newInv) {
		this.inventory=newInv;
	}

	@XmlElement
	public int getX() {
		return xPos;
	}

	public void setX(int x) {
		this.xPos=x;
	}

	@XmlElement
	public int getY() {
		//if there are any other ideas let me know.
		return this.yPos;
	}

	public void setY(int y) {
		yPos=y;
	}


	/**
	 * special class methods
	 */
	public void pickUp(WorldObject ob) {
		if(ob instanceof Holdable) {
			if(inventory.size()<16)
			inventory.add(ob);
		}
	}

	public void dropItem(WorldObject ob){
	  if(ob instanceof Holdable){
	    inventory.remove(ob);
    }
  }

	/**
	 * method for unlocking a door, unsure if we need anything for locking a door again;
	 * @param door
	 */
	public void unlock(Door door) {
		for(WorldObject object : inventory) {
			if(object instanceof KeyObject) {
				if(object.getColor().equals(door.getColor()))door.setIsLocked(false);
			}
		}

	}

	/**
	 * method for moving character to another room
	 * @param d
	 */
	public void moveRoom(Direction d) {
		switch(d) {
		case NORTH:
			setY(yPos-1);
			break;
		case WEST:
			setX(xPos-1);
			break;
		case SOUTH:
			setY(yPos+1);
			break;
		case EAST:
			setX(xPos+1);
		}
	}

	/**
	 * Method to return a string containing the description of an object
	 * when a player examines it
	 * @param object
	 * @return
	 */
	public String examineObject(WorldObject object) {
		return object.getDescription();
	}

//	public void craftKey() {
//		int sameKeyComp = 0;
//		List<KeyComponent> keyComps = new ArrayList<>();
//		for(int i = 0 ; i < inventory.size()-1 ; i++) {
//			if(inventory.get(i) instanceof KeyComponent) {
//				KeyComponent comp1 = (KeyComponent) inventory.get(i);
//				for(int o = i+1; i < inventory.size() ; i++) {
//					if(inventory.get(o) instanceof KeyComponent && object!=object2) {
//						if(comp1.getColor().equals(object2.getColor())) {
//							keyComps.add(object2);
//							if(keyComps.size()==2) {
//								keyComps.add(object);
//								break;
//							}
//						}
//					}
//				}
//			}
//			keyComps = new ArrayList<>();
//		}
//
//	}

	public Direction getRight(){
		return Direction.values()[(Math.floorMod(perspective.ordinal()+1, 4))];
	}

	public Direction getLeft(){
		return Direction.values()[(Math.floorMod(perspective.ordinal()-1, 4))];
	}
}
