package GameWorld;

import java.util.List;

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

	public void MovePlayer(Direction d) {
		if (rooms[player.getX()][player.getY()].hasNeighbor(d, rooms) &&
				rooms[player.getX()][player.getY()].getWall(d).hasDoor()
				&& !rooms[player.getX()][player.getY()].getWall(d).door.getIsLocked()) {
			player.moveRoom(d);
		}
	}

	public void pickUp(WorldObject object) {
		// this returns the list of objects from players perspective and removes object from it
		rooms[player.getX()][player.getY()].getContents().get(player.getPerspective()).remove(object);
		player.pickUp(object);
	}

	public List<WorldObject> getObjectsInView() {
		return rooms[player.getX()][player.getY()].getContents().get(player.getPerspective());
	}




}
