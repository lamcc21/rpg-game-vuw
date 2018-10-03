package GameWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import GameWorld.GameWorld.Direction;

/**
 * added to the room class;
 * certain methods have to be static to allow the renderer to work. will update
 * Making default room size 10
 * @author francis raureti
 *
 */

@XmlRootElement
public class Room {

	static final int SIZE = 10;

	static List<WorldObject> contents;
	static Map<Direction,Room>neighbors;
	static Map<Direction,Wall>walls;

	public Room(List<WorldObject> contents, Map<Direction,Room>neighbors, Map<Direction,Wall>walls) {
		this.contents=contents;
		this.neighbors=neighbors;
		this.walls=walls;
	}

	public Room() {}

	@XmlElement
	public List<WorldObject> getContents(){
		return new ArrayList<WorldObject>(this.contents);
	}

	public void setContents(List<WorldObject> contents) {
		this.contents=contents;
	}

	@XmlElement
	public Map<Direction,Room> getNeighbors(){
		return new HashMap<Direction,Room>(this.neighbors);
	}


	public void setNeighbors(Map<Direction,Room>  neighbors) {
		this.neighbors=neighbors;
	}

	@XmlElement
	public static Map<Direction,Wall> getWalls(){
		return new HashMap<Direction,Wall>(walls);
	}

	public void setWalls(Map<Direction,Wall>  walls) {
		Room.walls=walls;
	}

	public Wall getWall(Direction d) {
		return walls.get(d);
	}


	/**
	 * method for checking if room has neighbor in a particular direction
	 * @param Direction d
	 * @return true/false
	 */
	public boolean hasNeighbor(Direction d) {
		if(neighbors.get(d)!=null) {
			return true;
		}
		return false;
	}

	/**
	 * method for iterating through room nodes and returning a list
	 * ------MUST BE PASSED AN EMPTY LIST AS INITIAL CONDITION-----
	 * @param EMPTY - ArrayList<Room>
	 * @return nice list of rooms
	 */
	public List<Room> getRooms(List<Room> rooms){
		rooms.add(this);
		for(Direction d : Direction.values()) {
			if(hasNeighbor(d)) {
				Room neighbor =neighbors.get(d);
				if(!rooms.contains(neighbor)) {
					neighbor.getRooms(rooms);
				}
			}
		}
		return rooms;
	}

}
