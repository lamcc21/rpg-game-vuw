package Persistence;

import GameWorld.*;
import GameWorld.Color;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameWorldFactory {
    public static void main(String[] arg) throws IOException {

        //TODO: generate a whole map Xml
        Room rooms[][] = new Room[3][3];

        //make room walls
        Map<GameWorld.Direction, Wall> walls1 = new HashMap<>();
        Color cyan = new Color(224,255,255);
        Wall northWall = new Wall(true, GameWorld.Direction.NORTH);
        walls1.put(GameWorld.Direction.NORTH, northWall);
        Wall eastWall = new Wall(true, GameWorld.Direction.EAST);
        walls1.put(GameWorld.Direction.EAST, eastWall);
        Wall southWall = new Wall(true, GameWorld.Direction.SOUTH);
        walls1.put(GameWorld.Direction.SOUTH, southWall);
        Door westDoor = new Door(false, cyan);
        Wall westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
        walls1.put(GameWorld.Direction.WEST, westWall);

        //make room contents
        Map<GameWorld.Direction, ArrayList<WorldObject>> contents1= new HashMap<>();
        KeyComponent pen = new KeyComponent(1, 1, 1, 1, 1, 1, "Pen", "A Nice Pen", GameWorld.Direction.NORTH, cyan);
        KeyComponent plant = new KeyComponent(5, 5, 1, 50, 50, 1, "Pot plant", "Greenery", GameWorld.Direction.NORTH, cyan);
        KeyComponent painting = new KeyComponent(7, 10, 1, 50, 50, 1, "Painting", "its A Painting", GameWorld.Direction.EAST, cyan);
        ArrayList<WorldObject> northComponents = new ArrayList<>();
        ArrayList<WorldObject> westComponents = new ArrayList<>();
        northComponents.add(pen);
        northComponents.add(plant);
        westComponents.add(painting);
        contents1.put(GameWorld.Direction.NORTH, northComponents);
        contents1.put(GameWorld.Direction.WEST, westComponents);

        //initialise room
        Room room1 = new Room(contents1, walls1, 0, 0);
        rooms[0][0] = room1;

        //make room walls
        Map<GameWorld.Direction, Wall> walls2 = new HashMap<>();
        northWall = new Wall(true, GameWorld.Direction.NORTH);
        walls2.put(GameWorld.Direction.NORTH, northWall);
        eastWall = new Wall(true, GameWorld.Direction.EAST);
        walls2.put(GameWorld.Direction.EAST, eastWall);
        southWall = new Wall(true, GameWorld.Direction.SOUTH);
        walls2.put(GameWorld.Direction.SOUTH, southWall);
        westDoor = new Door(false, cyan);
        westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
        walls2.put(GameWorld.Direction.WEST, westWall);

        //make room contents
        Map<GameWorld.Direction, ArrayList<WorldObject>> contents2= new HashMap<>();

        //initialise room
        Room room2 = new Room(contents1, walls1, 0, 0);
        rooms[1][0] = room1;

        //initialise player
        Player player = new Player(0, 0);
        //add items that start in the players inventory

        GameWorld game = new GameWorld(player, rooms);

        Persistence.ObjectToXml(game, "prototypeGame.xml");
    }
}
