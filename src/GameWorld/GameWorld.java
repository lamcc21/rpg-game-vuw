package GameWorld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
/**
 * For SWEN225 Group Project - Francis Raureti
 */
@XmlRootElement
public class GameWorld {

	private Player player;
	private Room[][] rooms;

	public enum Direction{
		NORTH,
		EAST,
		SOUTH,
		WEST;
	}

	public GameWorld(Player p , Room[][] rooms) {
		player=p;
		this.rooms=rooms;
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
	public Room getRoom(int x,int y) {
		return rooms[x][y];
	}

	public void setRoom(Room[][] rooms) {
		this.rooms=rooms;
	}

	public void MovePlayer(Player player, Direction d) {
		if (rooms[player.getX()][player.getY()].hasNeighbor(d, rooms)) {
			player.moveRoom(d);
		}
	}




}
