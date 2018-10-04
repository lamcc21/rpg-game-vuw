package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
/**
 * For SWEN225 Group Project - Francis Raureti
 */
@XmlRootElement
public class GameWorld {

	//Planning to organize rooms like a graph where every room is a node
	//rooms can only be connected to a max of four other rooms, a large
	//room will consist of two or more rooms with invisible walls connecting
	//them.

	//if there are any other ideas let me know.

	/**
	 * Set all getters and setters to static to allow Rendered to handle objects
	 * hopefully it doent cause any problems
	 */

	private Player player;
	private Room room;

	public enum Direction{
		NORTH,
		EAST,
		SOUTH,
		WEST;
	}

	public GameWorld(Player p , Room R) {
		player=p;
		room=R;
	}

	public GameWorld() {}

	@XmlElement
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player=p;
	}

	@XmlElement
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room r) {
		room=r;
	}




}
