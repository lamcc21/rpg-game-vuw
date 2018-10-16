package Persistence;

import GameWorld.*;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameWorldFactory {
  public static void main(String[] arg) throws IOException {
    makeBigMap();
    makeSmallMap();
  }

  private static void makeBigMap(){
    Room rooms[][] = new Room[3][3];
    Room room;

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
    walls = new HashMap<>();
    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(true, GameColor.cyan);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westWall = new Wall(true, GameWorld.Direction.WEST);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Plasma conduit", "An essential plasma utility", GameWorld.Direction.NORTH, GameColor.cyan);
    component2 = new KeyComponent(2, 1, 0, 1, 1, 1, "Computer console", "Wall mounted for convenience", GameWorld.Direction.NORTH, GameColor.cyan);
    contained = new ArrayList<>();
    contained.add(component1);
    container1 = new Container(2, 1, 2, 1, 1, 1, "Crate", "A simple crate", GameWorld.Direction.NORTH, contained);
    northComponents.add(component2);
    northComponents.add(container1);
    component3 = new KeyComponent(1, 0, 0, 1, 1, 1, "Vial", "A vial of a mysterious substance", GameWorld.Direction.SOUTH, GameColor.cyan);
    southComponents.add(component3);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls,  0, 0);
    rooms[0][0] = room;

    //initialise room 2
    walls = new HashMap<>();
    northComponents = new ArrayList<>();
    eastComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    westComponents= new ArrayList<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(false, GameColor.cyan);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(true, GameColor.cyan);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Optic spanner", "For getting a grip on light", GameWorld.Direction.WEST, GameColor.purple);
    contained = new ArrayList<>();
    contained.add(component1);
    container1 = new Container(2, 0, 0, 2, 2, 1, "Cupboard", "A very useful device for putting things in", GameWorld.Direction.WEST, contained);
    westComponents = new ArrayList<>();
    westComponents.add(container1);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 1, 0);
    rooms[1][0] = room;

    //initialise room 3
    walls = new HashMap<>();
    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastWall = new Wall(true, GameWorld.Direction.EAST);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southDoor = new Door(true, GameColor.purple);
    southWall = new Wall(true, GameWorld.Direction.SOUTH, southDoor);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(false, GameColor.cyan);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    component1 = new KeyComponent(0, 0, 0, 1, 1, 1, "Boots", "Made for walking", GameWorld.Direction.EAST, GameColor.purple);
    eastComponents = new ArrayList<>();
    eastComponents.add(component1);
    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Plasma conduit", "Another one", GameWorld.Direction.SOUTH, GameColor.purple);
    southComponents = new ArrayList<>();
    southComponents.add(component1);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 2, 0);
    rooms[2][0] = room;

    //initialise room 4
    walls = new HashMap<>();


    northDoor = new Door(false, GameColor.purple);
    northWall = new Wall(true, GameWorld.Direction.NORTH, northDoor);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastWall = new Wall(true, GameWorld.Direction.EAST);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(true, GameColor.green);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();;

    component1 = new KeyComponent(1, 0, 2, 1, 1, 1, "Battery pack", "Optimised energy storage", GameWorld.Direction.EAST, GameColor.green);
    component2 = new KeyComponent(1, 0, 2, 1, 1, 1, "Rare metal", "Very valuable", GameWorld.Direction.EAST, GameColor.gold);
    contained = new ArrayList<>();
    contained.add(component1);
    contained.add(component2);
    container1 = new Container(1, 0, 2, 1, 2, 1, "Barrel", "Storage barrel", GameWorld.Direction.EAST, contained);
    eastComponents = new ArrayList<>();
    eastComponents.add(container1);
    component3 = new KeyComponent(1, 3, 1, 1, 1, 1, "Ceiling fan", "Keeps you cool", GameWorld.Direction.WEST, GameColor.green);
    westComponents = new ArrayList<>();
    westComponents.add(component3);
    component4 = new KeyComponent(2, 1, 0, 1, 1, 1, "Computer console", "Provides access to the mainframe", GameWorld.Direction.SOUTH, GameColor.green);
    southComponents = new ArrayList<>();
    southComponents.add(component4);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 2, 1);
    rooms[2][1] = room;

    //initialise room 5
    walls = new HashMap<>();


    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(false, GameColor.green);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(true, GameColor.green);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Helmet", "Safety is key", GameWorld.Direction.NORTH, GameColor.gold);
    contained.add(component1);
    container1 = new Container(1, 0, 1, 1, 1, 1, "Crate", "A humble crate", GameWorld.Direction.NORTH, contained);
    northComponents.add(container1);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 1, 1);
    rooms[1][1] = room;

    //initialise room 6
    walls = new HashMap<>();


    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(false, GameColor.green);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southDoor = new Door(true, GameColor.gold);
    southWall = new Wall(true, GameWorld.Direction.SOUTH, southDoor);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westWall = new Wall(true, GameWorld.Direction.WEST);
    walls.put(GameWorld.Direction.WEST, westWall);

    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();
    component1 = new KeyComponent(0, 1, 2, 1, 1, 1, "Glowing orb", "Glowing orb of wonder, bow in splendor", GameWorld.Direction.NORTH, GameColor.gold);
    northComponents.add(component1);
    component2 = new KeyComponent(1, 1, 2, 1, 1, 1, "Tesseract", "A fourth dimensional hypercube", GameWorld.Direction.WEST, GameColor.brown);
    westComponents.add(component2);
    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 0, 1);
    rooms[0][1] = room;

    //initialise room 7
    walls = new HashMap<>();

    northDoor = new Door(false, GameColor.brown);
    northWall = new Wall(true, GameWorld.Direction.NORTH, northDoor);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(true, GameColor.brown);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westWall = new Wall(true, GameWorld.Direction.WEST);
    walls.put(GameWorld.Direction.WEST, westWall);

    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();
    component1 = new KeyComponent(1, 1, 2, 1, 1, 1, "Mirror", "See who you really are", GameWorld.Direction.SOUTH, GameColor.silver);
    southComponents = new ArrayList<>();
    southComponents.add(component1);
    component2 = new KeyComponent(2, 0, 1, 1, 1, 1, "Radioactive waste", "Secure storage facility for radioactive waste", GameWorld.Direction.WEST, GameColor.brown);
    westComponents = new ArrayList<>();
    westComponents.add(component2);
    component3 = new KeyComponent(0, 1, 0, 1, 2, 1, "Humanoid android", "An autonomous humanoid personal assistant", GameWorld.Direction.EAST, GameColor.brown);
    eastComponents = new ArrayList<>();
    eastComponents.add(component3);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 0, 2);
    rooms[0][2] = room;

    //initialise room 8
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(true, GameColor.brown);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(false, GameColor.brown);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();
    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "The holy grail", "A legendary relic", GameWorld.Direction.SOUTH, GameColor.silver);
    southComponents = new ArrayList<>();
    contained = new ArrayList<>();
    contained.add(component1);
    container1 = new Container(1, 0, 1, 2, 1, 1, "Table", "Antique device once used on earth", GameWorld.Direction.SOUTH, contained);
    southComponents.add(container1);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 1, 2);
    rooms[1][2] = room;

    //initialise room 9
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastWall = new Wall(true, GameWorld.Direction.EAST);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southDoor = new Door(true, GameColor.silver);
    southWall = new Wall(true, GameWorld.Direction.SOUTH, southDoor);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(false, GameColor.brown);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);
    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Apple", "A juicy sweet red apple", GameWorld.Direction.WEST, GameColor.silver);
    westComponents = new ArrayList<>();
    westComponents.add(component1);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 2, 2);
    rooms[2][2] = room;

    //initialise player
    Player player = new Player(0, 0);

    GameWorld game = new GameWorld(player, rooms);

    try {
      Persistence.ObjectToXml(game, new File("bigMap.xml"));
    } catch (JAXBException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void makeSmallMap(){
    Room rooms[][] = new Room[2][2];
    Room room;

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
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(true, GameColor.cyan);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westWall = new Wall(true, GameWorld.Direction.WEST);
    walls.put(GameWorld.Direction.WEST, westWall);

    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Plasma conduit", "An essential plasma utility", GameWorld.Direction.NORTH, GameColor.cyan);
    component2 = new KeyComponent(2, 1, 0, 1, 1, 1, "Computer console", "Wall mounted for convenience", GameWorld.Direction.NORTH, GameColor.cyan);
    contained = new ArrayList<>();
    contained.add(component1);
    container1 = new Container(2, 1, 2, 1, 1, 1, "Crate", "A simple crate", GameWorld.Direction.NORTH, contained);
    northComponents.add(component2);
    northComponents.add(container1);
    component3 = new KeyComponent(1, 0, 0, 1, 1, 1, "Vial", "A vial of a mysterious substance", GameWorld.Direction.SOUTH, GameColor.cyan);
    southComponents.add(component3);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls,  0, 0);
    rooms[0][0] = room;

    //initialise room 2
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastWall = new Wall(true, GameWorld.Direction.EAST);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southDoor = new Door(true, GameColor.purple);
    southWall = new Wall(true, GameWorld.Direction.SOUTH, southDoor);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(false, GameColor.cyan);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();

    component1 = new KeyComponent(0, 0, 0, 1, 1, 1, "Boots", "Made for walking", GameWorld.Direction.EAST, GameColor.purple);
    eastComponents.add(component1);
    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Plasma conduit", "Another one", GameWorld.Direction.SOUTH, GameColor.purple);
    southComponents.add(component1);
    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Optic spanner", "For getting a grip on light", GameWorld.Direction.WEST, GameColor.purple);
    contained = new ArrayList<>();
    contained.add(component1);
    container1 = new Container(2, 0, 0, 2, 2, 1, "Cupboard", "A very useful device for putting things in", GameWorld.Direction.WEST, contained);
    westComponents.add(container1);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 1, 0);
    rooms[1][0] = room;

    //initialise room 3
    walls = new HashMap<>();

    northDoor = new Door(false, GameColor.purple);
    northWall = new Wall(true, GameWorld.Direction.NORTH, northDoor);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastWall = new Wall(true, GameWorld.Direction.EAST);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southWall = new Wall(true, GameWorld.Direction.SOUTH);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westDoor = new Door(true, GameColor.gold);
    westWall = new Wall(true, GameWorld.Direction.WEST, westDoor);
    walls.put(GameWorld.Direction.WEST, westWall);

    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();;

    component1 = new KeyComponent(1, 0, 2, 1, 1, 1, "Battery pack", "Optimised energy storage", GameWorld.Direction.EAST, GameColor.gold);
    component2 = new KeyComponent(1, 0, 2, 1, 1, 1, "Rare metal", "Very valuable", GameWorld.Direction.EAST, GameColor.gold);
    contained = new ArrayList<>();
    contained.add(component1);
    contained.add(component2);
    container1 = new Container(1, 0, 2, 1, 2, 1, "Barrel", "Storage barrel", GameWorld.Direction.EAST, contained);
    eastComponents.add(container1);
    component3 = new KeyComponent(1, 3, 1, 1, 1, 1, "Ceiling fan", "Keeps you cool", GameWorld.Direction.WEST, GameColor.gold);
    westComponents.add(component3);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 1, 1);
    rooms[1][1] = room;

    //initialise room 4
    walls = new HashMap<>();

    northWall = new Wall(true, GameWorld.Direction.NORTH);
    walls.put(GameWorld.Direction.NORTH, northWall);
    eastDoor = new Door(false, GameColor.gold);
    eastWall = new Wall(true, GameWorld.Direction.EAST, eastDoor);
    walls.put(GameWorld.Direction.EAST, eastWall);
    southDoor = new Door(true, GameColor.silver);
    southWall = new Wall(true, GameWorld.Direction.SOUTH, southDoor);
    walls.put(GameWorld.Direction.SOUTH, southWall);
    westWall = new Wall(true, GameWorld.Direction.WEST);
    walls.put(GameWorld.Direction.WEST, westWall);

    northComponents = new ArrayList<>();
    westComponents = new ArrayList<>();
    southComponents= new ArrayList<>();
    eastComponents= new ArrayList<>();

    component1 = new KeyComponent(1, 0, 1, 1, 1, 1, "Helmet", "Safety is key", GameWorld.Direction.NORTH, GameColor.silver);
    contained.add(component1);
    container1 = new Container(1, 0, 1, 1, 1, 1, "Crate", "A humble crate", GameWorld.Direction.NORTH, contained);
    northComponents.add(container1);
    component1 = new KeyComponent(2, 0, 1, 1, 1, 1, "Plasma conduit", "Another one", GameWorld.Direction.WEST, GameColor.silver);
    component2 = new KeyComponent(0, 0, 1, 1, 1, 1, "Glowing orb", "Glowing orb of wonder, bow in splendor", GameWorld.Direction.WEST, GameColor.silver);
    westComponents.add(component1);
    westComponents.add(component2);

    room = new Room(northComponents,eastComponents,southComponents,westComponents, walls, 0, 1);
    rooms[0][1] = room;

    //initialise player
    Player player = new Player(0, 0);

    GameWorld game = new GameWorld(player, rooms);

    try {
      Persistence.ObjectToXml(game, new File("smallMap"));
    } catch (JAXBException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
