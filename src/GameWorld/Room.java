package GameWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import GameWorld.GameWorld.Direction;

/**
 *
 * @author francis raureti
 */

@XmlRootElement
public class Room {

	public final static int SIZE = 10;

	private Map<Direction, ArrayList<WorldObject>> contents;
	private Map<Direction,Wall>walls;
	private int x;
	private int y;

	public Room(Map<Direction, ArrayList<WorldObject>> contents, Map<Direction,Wall>walls,int x , int y) {
		this.contents=contents;
		this.walls=walls;
		this.x=x;
		this.y=y;
	}

	public Room() {}

	@XmlElement
	public Map<Direction, ArrayList<WorldObject>> getContents(){
		return this.contents;
	}

	public void setContents(Map<Direction, ArrayList<WorldObject>> contents) {
		this.contents=contents;
	}

	@XmlElement
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x=x;
	}

	@XmlElement
	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y=y;
	}

	@XmlElement
	public Map<Direction,Wall> getWalls(){
		return walls;
	}

	public void setWalls(Map<Direction,Wall>  walls) {
		this.walls= walls;
	}

	public Wall getWall(Direction d) {
		return walls.get(d);
	}

	public void setWall(Direction d ,Wall w) {
		this.walls.put(d, w);
	}

	public Room getNeighbor(Direction dir, Room[][] rooms) {
		if(hasNeighbor(dir, rooms)) {
			switch(dir) {
			case NORTH:
				return rooms[x][y-1];
			case EAST:
				return rooms[x+1][y];
			case SOUTH:
				return rooms[x][y+1];
			case WEST:
				return rooms[x-1][y];
			}

		}
		return null;
	}


	/**
	 * method for checking if room has neighbor in a particular direction
	 * @param Direction d
	 * @return true/false
	 */
	public boolean hasNeighbor(Direction direction, Room[][] rooms) {
		switch(direction) {
		case NORTH:
			if(this.y!=0) {
				if(rooms[this.x][this.y-1]!=null) {
					return true;
				}
			}
			break;
		case EAST:
			if(this.x!=rooms.length-1) {
				if(rooms[this.x+1][this.y]!=null) {
					return true;
				}
			}
			break;
		case SOUTH:
			if(this.y!=rooms.length-1) {
				if(rooms[this.x][this.y+1]!=null) {
					return true;
				}
			}
			break;
		case WEST:
			if(this.x!=0) {
				if(rooms[this.x-1][this.y]!=null) {
					return true;
				}
			}
			break;
		}
		return false;
	}

	/**
	 * method for iterating through room nodes and returning a list
	 * ------MUST BE PASSED AN EMPTY LIST AS INITIAL CONDITION-----
	 * @param EMPTY - ArrayList<Room>
	 * @return nice list of rooms
	 */
	//public List<Room> getRooms(List<Room> rooms){
	//
	//}

}
