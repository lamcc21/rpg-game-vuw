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

        //TODO: These are just example objects we will replace
        KeyComponent pen = new KeyComponent(1, 1, 1, 1, 1, 1, "Pen", "A Nice Pen", GameWorld.Direction.NORTH, new Color(0, 0, 255));
        KeyComponent plant = new KeyComponent(5, 5, 1, 50, 50, 1, "Pot plant", "Greenery", GameWorld.Direction.NORTH, new Color(0, 0, 255));
        KeyComponent painting = new KeyComponent(7, 10, 1, 50, 50, 1, "Painting", "its A Painting", GameWorld.Direction.NORTH, new Color(0, 0, 255));
        KeyComponent chair = new KeyComponent(2, 15, 1, 50, 50, 1, "Chair", "its A Chair", GameWorld.Direction.NORTH, new Color(0, 0, 255));
        KeyComponent table = new KeyComponent(6, 9, 1, 50, 50, 1, "Table", "its A Table ", GameWorld.Direction.NORTH, new Color(0, 0, 255));

        ArrayList<WorldObject> northComponents = new ArrayList<>();
        northComponents.add(pen);
        northComponents.add(plant);
        northComponents.add(painting);

        contents.put(GameWorld.Direction.NORTH, northComponents);

        Room room = new Room(contents, walls, 0, 0);
        Room rooms[][] = new Room[1][1];
        rooms[0][0] = room;

        Player player = new Player(0, 0);
        player.pickUp(pen);
        player.pickUp(plant);
        player.pickUp(painting);
        player.pickUp(chair);
        player.pickUp(table);

        GameWorld game = new GameWorld(player, rooms);

        Persistence.ObjectToXml(game, "prototypeGame.xml");
    }
}
