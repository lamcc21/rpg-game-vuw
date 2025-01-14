package GameWorld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
		WEST
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

	@XmlElement
	public Room[][] getRooms() {
		return rooms;
	}

	public void setRooms(Room[][] rooms) {
		this.rooms = rooms;
	}

	public void setPlayer(Player p) {
		player=p;
	}

	public Room getRoom(int x,int y) {
		return rooms[x][y];
	}

	public void movePlayer(Direction d) throws EndGameException {
		if (rooms[player.getX()][player.getY()].hasNeighbor(d, rooms) &&
				rooms[player.getX()][player.getY()].getWall(d).hasDoor()
				&& !rooms[player.getX()][player.getY()].getWall(d).door.getIsLocked()) {
			if(rooms[player.getX()][player.getY()].getWall(d).door.getGameColor().toString()
					.equals("silver")) {throw (new EndGameException());}
			player.moveRoom(d);
		}
	}

	public void pickUp(WorldObject object) {
		// this returns the list of objects from players perspective and removes object from it
		List<WorldObject> contents = rooms[player.getX()][player.getY()].getContents(player.getPerspective());
		WorldObject cont = null;
		player.pickUp(object);
		if(contents.contains(object)) {
			contents.remove(object);
		}else {
			for(WorldObject ob : contents) {
				if(ob.contents.contains(object)) {
					cont = ob;
					}
			}
			if(cont!=null) {
				cont.contents.remove(object);
			}
		}
	}



	public void drop(WorldObject object) {
		player.dropItem(object);
		List<WorldObject>objects = getObjectsInView();
		boolean added =false;
		for(WorldObject ob:objects) {
			if(object instanceof Container && ((Container) object).getIsSelected()) {
				((Container)ob).addWorldObject(object);
				added = true;
			}
		}
	}

	public List<WorldObject> getObjectsInView() {
		if(player.getX()<0 || player.getX()>=rooms.length ||
				player.getY()<0 || player.getY()>=rooms[0].length) {return null;}
		return rooms[player.getX()][player.getY()].getContents(player.getPerspective());
	}
}
