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

	}

}
