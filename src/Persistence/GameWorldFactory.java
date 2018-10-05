package Persistence;

import GameWorld.*;
import GameWorld.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameWorldFactory {
    public static void main(String[] arg){

        Map<GameWorld.Direction, ArrayList<WorldObject>> contents= new HashMap<>();
        Map<GameWorld.Direction, Wall> walls = new HashMap<>();

        Color blue = new Color(0, 0, 255);
        Wall northWall = new Wall(true, GameWorld.Direction.NORTH);
        walls.put(GameWorld.Direction.NORTH, northWall);
        Door eastDoor = new Door(false, blue);
        Wall eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
        walls.put(GameWorld.Direction.EAST, eastWall);
        Door southDoor = new Door(false, blue);
        Wall southWall = new Wall(true, GameWorld.Direction.SOUTH, southDoor);
        walls.put(GameWorld.Direction.SOUTH, southWall);
        Wall westWall = new Wall(true, GameWorld.Direction.WEST);
        walls.put(GameWorld.Direction.WEST, westWall);

        Room room = new Room(contents, walls, 0, 0);
        Room rooms[][] = new Room[1][1];
        rooms[0][0] = room;
        Player player = new Player(0, 0);


        GameWorld game = new GameWorld(player, rooms);

        Persistence.ObjectToXml(game, "prototypeGame.xml");
    }
}
