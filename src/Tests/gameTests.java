package Tests;

import GameWorld.*;
import Persistence.Persistence;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class gameTests {
    static GameWorld gameWorld;


    public static void initialise(){

        try {
            gameWorld = Persistence.XmlToObject(new File("prototypeGame1.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
    
    @Test
    public void testLockedDoor() {
    	initialise();
        Player p = gameWorld.getPlayer();
        Room r = gameWorld.getRoom(p.getX(), p.getY());
        assert(p.getX()==0 && p.getY()==0);
        gameWorld.movePlayer(GameWorld.Direction.EAST);
        assert(p.getX()==0 && p.getY()==0);
    }

    @Test
    public void testMoveRoom() {
    	initialise();
        List<WorldObject> expected = new ArrayList<>();
        Pickup(expected);
        Player p = gameWorld.getPlayer();
        Room r = gameWorld.getRoom(p.getX(), p.getY());
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getRight());
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getRight());
        Pickup(expected);
        gameWorld.getPlayer().craft(GameColor.cyan);
        p.unlock(r.getWall(GameWorld.Direction.EAST).getDoor());
        gameWorld.movePlayer(GameWorld.Direction.EAST);
        assert(p.getX()==1 && p.getY()==0);
    }

    @Test
    public void testPickup2() {
    	initialise();
        Player p = gameWorld.getPlayer();
        Room r = gameWorld.getRoom(p.getX(), p.getY());
        List<WorldObject> objects = new ArrayList<>(r.getContents(p.getPerspective()));
        List<String> expected =  Arrays.asList("Vial","Computer console","Plasma conduit");
        for (WorldObject object : objects) {
        	gameWorld.pickUp(object);
        }
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getLeft());
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getLeft());
        objects = new ArrayList<>(r.getContents(p.getPerspective()));
        for (WorldObject object : objects) {
        	gameWorld.pickUp(object);
        }
        for(WorldObject ob : p.getInventory()) {
        	System.out.println(ob.toString());
        }
       
        assert(expected.equals(p.getInventory()));
    }

    @Test
    public void testPickup(){
    	initialise();
        List<WorldObject> expected = new ArrayList<>();
        Pickup(expected);
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getRight());
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getRight());
        Pickup(expected);
        gameWorld.getPlayer().craft(GameColor.cyan);
    }

    @Test
    public void testCraftKey(){
    	initialise();
        List<WorldObject> expected = new ArrayList<>();
        Pickup(expected);
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getRight());
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getRight());
        Pickup(expected);
        System.out.println(gameWorld.getPlayer().getInventory().toString());
        gameWorld.getPlayer().craft(GameColor.cyan);
        assertTrue(gameWorld.getPlayer().getInventory().contains(new KeyObject(GameColor.cyan)), "no key found");
    }

    private void Pickup(List<WorldObject> expected) {
        Player player = gameWorld.getPlayer();
        Room room = gameWorld.getRoom(player.getX(), player.getY());
        for (WorldObject object : room.getContents(player.getPerspective())) {
            if (object.getContents() != null) {
                for (WorldObject childObject : object.getContents()) {
                    player.pickUp(childObject);
                    expected.add(childObject);
                }
            } else {
                player.pickUp(object);
                expected.add(object);
            }
        }
    }

}
