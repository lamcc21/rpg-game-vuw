package Persistence;

import GameWorld.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameWorldFactory {
  public static void main(String[] arg) throws IOException {

    Room rooms[][] = new Room[3][3];

    //initialise colours
    Color cyan = new Color(67,125,128);
    Color purple = new Color(75, 66, 121);
    Color green = new Color(63,99, 37);
    Color blue = new Color(0, 0, 255);
    Color gold = new Color(170, 170, 36);
    Color silver = new Color(102, 102, 102);

    Room room;

    Map<GameWorld.Direction, ArrayList<WorldObject>> contents;
    Map<GameWorld.Direction, Wall> walls;

    Wall northWall;
    Wall southWall;
    Wall eastWall;
    Wall westWall;

    Door northDoor;
    Door southDoor;
    Door eastDoor;
    Door westDoor;

    ArrayList<WorldObject> northComponents;
    ArrayList<WorldObject> southComponents;
    ArrayList<WorldObject> eastComponents;
    ArrayList<WorldObject> westComponents;

    KeyComponent component1;
    KeyComponent component2;
    KeyComponent component3;
    KeyComponent component4;

    Container container1;
    Container container2;

    List<WorldObject> contained;

    //initialise room 1
    contents = new HashMap<>();
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(true, cyan);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westWall = new Wall(true, GameWorld.Direction.WEST);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Plasma conduit", "An essential plasma utility", GameWorld.Direction.NORTH, cyan);
    component2 = new KeyComponent(2, 2, 0, 1, 1, 1, "Computer console", "Wall mounted for convenience", GameWorld.Direction.NORTH, cyan);
    contained = new ArrayList<>();
    contained.add(component2);
    container1 = new Container(2, 1, 2, 1, 1, 1, "Crate", "A simple crate", GameWorld.Direction.NORTH, contained);
    northComponents = new ArrayList<>();
    northComponents.add(component1);
    northComponents.add(container1);
    component3 = new KeyComponent(1, 0, 0, 1, 1, 1, "Vile", "A vile of a mysterious substance", GameWorld.Direction.SOUTH, cyan);
    southComponents = new ArrayList<>();
    southComponents.add(component3);
    contents.put(GameWorld.Direction.NORTH, northComponents);
    contents.put(GameWorld.Direction.SOUTH, southComponents);

    room = new Room(contents, walls, 0, 0);
    rooms[0][0] = room;

    //initialise room 2
    contents = new HashMap<>();
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(false, cyan);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(true, cyan);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Optic spanner", "For getting a grip on light", GameWorld.Direction.WEST, purple);
    contained = new ArrayList<>();
    contained.add(component1);
    container1 = new Container(2, 0, 0, 2, 2, 1, "Cupboard", "A very useful device for putting things in", GameWorld.Direction.WEST, contained);
    westComponents = new ArrayList<>();
    westComponents.add(container1);
    contents.put(GameWorld.Direction.WEST, westComponents);

    room = new Room(contents, walls, 1, 0);
    rooms[1][0] = room;

    //initialise room 3
    contents = new HashMap<>();
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastWall = new Wall(true, GameWorld.Direction.EAST);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southDoor = new Door(true, purple);
    southWall = new Wall(true, GameWorld.Direction.SOUTH, southDoor);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(false, cyan);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(0, 0, 0, 1, 1, 1, "Boots", "Made for walking", GameWorld.Direction.EAST, purple);
    eastComponents = new ArrayList<>();
    eastComponents.add(component1);
    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Plasma conduit", "Another one", GameWorld.Direction.SOUTH, purple);
    southComponents = new ArrayList<>();
    southComponents.add(component1);
    contents.put(GameWorld.Direction.EAST, eastComponents);
    contents.put(GameWorld.Direction.SOUTH, southComponents);

    room = new Room(contents, walls, 2, 0);
    rooms[2][0] = room;

    //initialise room 4
    contents = new HashMap<>();
    walls = new HashMap<>();

    northDoor = new Door(false, purple);
    northWall = new Wall(true, GameWorld.Direction.NORTH, northDoor);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastWall = new Wall(true, GameWorld.Direction.EAST);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(true, green);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(1, 0, 2, 1, 1, 1, "Battery pack", "Optimised energy storage", GameWorld.Direction.EAST, green);
    component2 = new KeyComponent(1, 0, 2, 1, 1, 1, "Rare metal", "Very valuable", GameWorld.Direction.EAST, gold);
    contained = new ArrayList<>();
    contained.add(component1);
    contained.add(component2);
    container1 = new Container(1, 0, 2, 1, 2, 1, "Barrel", "Storage barrel", GameWorld.Direction.EAST, contained);
    eastComponents = new ArrayList<>();
    eastComponents.add(container1);
    component3 = new KeyComponent(1, 3, 1, 1, 1, 1, "Ceiling fan", "Keeps you cool", GameWorld.Direction.WEST, green);
    westComponents = new ArrayList<>();
    westComponents.add(component3);
    component4 = new KeyComponent(2, 1, 0, 1, 1, 1, "Computer console", "Provides access to the mainframe", GameWorld.Direction.SOUTH, green);
    southComponents = new ArrayList<>();
    southComponents.add(component4);
    contents.put(GameWorld.Direction.EAST, eastComponents);
    contents.put(GameWorld.Direction.WEST, westComponents);
    contents.put(GameWorld.Direction.SOUTH, southComponents);

    room = new Room(contents, walls, 2, 1);
    rooms[2][1] = room;

    //initialise room 5
    contents = new HashMap<>();
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(false, green);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(true, green);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Helmet", "Safety is key", GameWorld.Direction.NORTH, gold);
    contained = new ArrayList<>();
    contained.add(component1);
    container1 = new Container(1, 0, 1, 1, 1, 1, "Crate", "A humble crate", GameWorld.Direction.NORTH, contained);
    northComponents = new ArrayList<>();
    northComponents.add(container1);
    contents.put(GameWorld.Direction.NORTH, northComponents);

    room = new Room(contents, walls, 1, 1);
    rooms[1][1] = room;

    //initialise room 6
    contents = new HashMap<>();
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(false, green);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southDoor = new Door(true, gold);
    southWall = new Wall(true, GameWorld.Direction.SOUTH, southDoor);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westWall = new Wall(true, GameWorld.Direction.WEST);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(0, 1, 2, 1, 1, 1, "Glowing orb", "Glowing orb of wonder, bow in splendor", GameWorld.Direction.NORTH, gold);
    northComponents = new ArrayList<>();
    northComponents.add(component1);
    component2 = new KeyComponent(1, 1, 2, 1, 1, 1, "Tesseract", "A fourth dimensional hypercube", GameWorld.Direction.WEST, blue);
    westComponents = new ArrayList<>();
    westComponents.add(component2);
    contents.put(GameWorld.Direction.WEST, westComponents);
    contents.put(GameWorld.Direction.NORTH, northComponents);

    room = new Room(contents, walls, 0, 1);
    rooms[0][1] = room;

    //initialise room 7
    contents = new HashMap<>();
    walls = new HashMap<>();

    northDoor = new Door(false, gold);
    northWall = new Wall(true, GameWorld.Direction.NORTH, northDoor);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(true, blue);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westWall = new Wall(true, GameWorld.Direction.WEST);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(1, 1, 2, 1, 1, 1, "Mirror", "See who you really are", GameWorld.Direction.SOUTH, silver);
    southComponents = new ArrayList<>();
    southComponents.add(component1);
    component2 = new KeyComponent(2, 0, 1, 1, 1, 1, "Radioactive waste", "Secure storage facility for radioactive waste", GameWorld.Direction.WEST, blue);
    westComponents = new ArrayList<>();
    westComponents.add(component2);
    component3 = new KeyComponent(0, 1, 0, 1, 2, 1, "Humanoid android", "An autonomous humanoid personal assistant", GameWorld.Direction.EAST, blue);
    eastComponents = new ArrayList<>();
    eastComponents.add(component3);
    contents.put(GameWorld.Direction.WEST, westComponents);
    contents.put(GameWorld.Direction.SOUTH, southComponents);
    contents.put(GameWorld.Direction.EAST, eastComponents);

    room = new Room(contents, walls, 0, 2);
    rooms[0][2] = room;

    //initialise room 8
    contents = new HashMap<>();
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(true, blue);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(false, blue);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "The holy grail", "A legendary relic", GameWorld.Direction.SOUTH, silver);
    southComponents = new ArrayList<>();
    contained = new ArrayList<>();
    contained.add(component1);
    container1 = new Container(1, 0, 1, 2, 1, 1, "Table", "Antique device once used on earth", GameWorld.Direction.SOUTH, contained);
    southComponents.add(container1);
    contents.put(GameWorld.Direction.SOUTH, southComponents);

    room = new Room(contents, walls, 1, 2);
    rooms[1][2] = room;

    //initialise room 9
    contents = new HashMap<>();
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastWall = new Wall(true, GameWorld.Direction.EAST);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southDoor = new Door(true, silver);
    southWall = new Wall(true, GameWorld.Direction.SOUTH, southDoor);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(false, blue);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Apple", "A juicy sweet red apple", GameWorld.Direction.WEST, silver);
    westComponents = new ArrayList<>();
    westComponents.add(component1);
    contents.put(GameWorld.Direction.WEST, westComponents);

    room = new Room(contents, walls, 2, 2);
    rooms[2][2] = room;

    //initialise player
    Player player = new Player(0, 0);



    GameWorld game = new GameWorld(player, rooms);

    Persistence.ObjectToXml(game, "prototypeGame1.xml");
  }
}
