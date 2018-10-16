package Tests;

import GameWorld.*;
import Persistence.Persistence;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
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
    public void testPickup(){
    	initialise();
        List<WorldObject> expected = new ArrayList<>();
        Pickup(expected);
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getRight());
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getRight());
        Pickup(expected);
        assertEquals(expected, gameWorld.getPlayer().getInventory(), "inventory does not contain expected items");
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
