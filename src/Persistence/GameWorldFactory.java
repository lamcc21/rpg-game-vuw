package Persistence;

import GameWorld.GameWorld;
import GameWorld.KeyObject;
import GameWorld.Wall;
import GameWorld.Door;

import java.util.HashMap;
import java.util.Map;

public class GameWorldFactory {
    public static void main(String[] arg){

        //init room 1
        Map<GameWorld.Direction, GameWorld.WorldObject> contents= new HashMap<>();
        Map<GameWorld.Direction, GameWorld.Wall> walls = new HashMap<>();
        GameWorld.Door northDoor = new Door(false, );
        GameWorld.Wall northWall = new Wall(true, GameWorld.Direction.NORTH, northDoor);
        GameWorld.Wall eastWall = new Wall(true, GameWorld.Direction.EAST);


        GameWorld.Room room = new GameWorld.Room();
        GameWorld.Player player  = new GameWorld.Player();
        GameWorld game = new GameWorld();

        Persistence.ObjectToXml(game, "game.xml");
    }
}
