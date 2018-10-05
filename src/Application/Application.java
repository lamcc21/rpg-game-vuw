package Application;
import GameWorld.GameWorld;
import GameWorld.Wall;
import GameWorld.WorldObject;
import GameWorld.Room;
import GameWorld.Door;
import Persistence.Persistence;
import Renderer.CanvasPane;
import GameWorld.Color;
import GameWorld.Player;
import GameWorld.KeyComponent;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.exit;

/**
 * For SWEN225 Group Project - Lachlan McCrae
 */

public class Application extends JFrame{
   //Will be used for start screen
  private CanvasPane canvas;
  private GameWorld gameWorld;
  private InventoryPane inventory;

  private Application() throws IOException, InterruptedException {
    super("The Great Escape");
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    setUIFont(new javax.swing.plaf.FontUIResource("Futuro", Font.BOLD, 15));
    GameWorld gameWorld = createGameWorld(new File("prototypeGame.xml"));

    if(gameWorld != null){
      this.canvas = new CanvasPane(gameWorld);
      this.inventory = new InventoryPane(gameWorld.getPlayer().getInventory());

      gbc.anchor = GridBagConstraints.NORTH;

      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
      //This centers every window displayed

      gbc.gridx = 1;
      gbc.gridy = 0;
      add(canvas, gbc);

      gbc.fill = GridBagConstraints.VERTICAL;
      gbc.gridx = 0;
      gbc.gridy = 0;
      add(new BasicArrowButton(BasicArrowButton.WEST), gbc);

      gbc.gridx = 2;
      gbc.gridy = 0;
      add(new BasicArrowButton(BasicArrowButton.EAST), gbc);

      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.gridx = 1;
      gbc.gridy = 1;
      add(inventory, gbc);
      pack();
      //startListening();
    }else{
      JOptionPane.showMessageDialog(this, "Game Load Error");
      exit(0);
    }

    setVisible(true);
   // startListening();
  }

  private void startListening() throws InterruptedException {
    while(true){
      //inventory.updateInventoryGUI(gameWorld.getRoom().getContents()); //TODO: If Item is added or removed
      Thread.sleep(1000);
    }
  }

  private GameWorld createGameWorld(File saveFile) throws IOException {
//    return Persistence.XmlToObject(saveFile);
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

    KeyComponent pen = new KeyComponent(1, 1, 1, 1, 1, 1, "Pen", "A nice Pen", GameWorld.Direction.NORTH, new Color(0, 0, 255));

    Room room = new Room(contents, walls, 0, 0);
    Room rooms[][] = new Room[1][1];
    rooms[0][0] = room;
    Player player = new Player(0, 0);

    player.pickUp(pen);

    return new GameWorld(player, rooms);
  }

  public static void main(String[] args) throws IOException, InterruptedException { new Application();}

  private static void setUIFont(javax.swing.plaf.FontUIResource f){
    java.util.Enumeration keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get (key);
      if (value instanceof javax.swing.plaf.FontUIResource)
        UIManager.put (key, f);
    }
  }
}
