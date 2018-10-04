package GameWorld;



import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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

	Direction perspective;
	List<WorldObject> inventory;
	Room location;

	public Player(Room room) {
	  this.perspective=Direction.NORTH;
	  inventory = new ArrayList<WorldObject>();
	  this.location = room;
	}

	public Player() {};

	/**
	 * all getters and setters for fields in class
	 *
	 */

	@XmlElement
	public Direction getPerspective() {
		return this.perspective;
	}

	public void setPerspective(Direction newPer) {
		this.perspective=newPer;
	}

	@XmlElement
	public List<WorldObject> getInventory(){
		return new ArrayList<WorldObject>(inventory);
	}

	public void setInventory(List<WorldObject> newInv) {
		this.inventory=newInv;
	}

	@XmlElement
	public Room getLocation() {
		return location;
	}

	public void setLocation(Room newLoc) {
		this.location=newLoc;
	}

	/**
	 * special class methods
	 */


	// a question..
	// can player only pick up keys or will there be other objects that can be picked up?
	// need to discuss further before implementing
	public void pickUp(WorldObject ob) {
		if(ob instanceof Holdable) {
			if(inventory.size()<3)
			inventory.add(ob);
		}
	}

	/**
	 * method for unlocking a door, unsure if we need anything for locking a door again;
	 * @param door
	 */
	public void unlock(Door door) {
		if (inventory.contains(door.key)) {
			door.setIsLocked(false);
		}
	}

	/**
	 * method for moving character to another room
	 * @param d
	 */
	public void moveRoom(Direction d) {

		setLocation(location.getNeighbors().get(d));
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
}
