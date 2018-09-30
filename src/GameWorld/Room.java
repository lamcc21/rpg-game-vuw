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
 * certain methods have to be static to allow the rendered to work. will update
 * @author rauretfran
 *
 */

@XmlRootElement
public class Room {

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
	@SuppressWarnings("static-access")
	public void setContents(List<WorldObject> contents) {
		this.contents=contents;
	}

	@XmlElement
	public Map<Direction,Room> getNeighbors(){
		return new HashMap<Direction,Room>(this.neighbors);
	}

	@SuppressWarnings("static-access")
	public void setNeighbors(Map<Direction,Room>  neighbors) {
		this.neighbors=neighbors;
	}

	@XmlElement
	public Map<Direction,Wall> getWalls(){
		return new HashMap<Direction,Wall>(walls);
	}

	public void setWalls(Map<Direction,Wall>  walls) {
		Room.walls=walls;
	}

	public Wall getWall(Direction d) {
		return walls.get(d);
	}

}
