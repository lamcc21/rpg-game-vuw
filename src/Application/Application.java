package Application;
import GameWorld.GameWorld;
import GameWorld.WorldObject;
import Persistence.Persistence;
import Renderer.CanvasPane;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static java.lang.System.exit;

/**
 * For SWEN225 Group Project - Lachlan McCrae
 */

public class Application extends JFrame{

  private Application() throws IOException, InterruptedException {
    super("Have A Go Escaping");
    setLayout(new GridBagLayout());
    UIManager.put ("ToolTip.background", new Color(67, 125, 128));
    setUIFont(new javax.swing.plaf.FontUIResource("Futuro", Font.BOLD, 15));
    GameWorld gameWorld = createGameWorld(new File("prototypeGame.xml"));

    if(gameWorld != null){
      CanvasPane canvas = new CanvasPane(gameWorld);
      InventoryPane inventory = new InventoryPane(gameWorld);
      CraftingPane crafting = new CraftingPane(gameWorld);


      GridBagConstraints toprow = new GridBagConstraints();
      GridBagConstraints bottomrow = new GridBagConstraints();

      toprow.anchor = GridBagConstraints.NORTH;
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
      //This centers every window displayed

      toprow.fill = GridBagConstraints.VERTICAL;
      toprow.gridx = 0;
      toprow.gridy = 0;

      BasicArrowButton buttonwest = new BasicArrowButton(BasicArrowButton.WEST) {
        @Override public Dimension getPreferredSize() { return new Dimension(30, 350);}
      };
      add(buttonwest, toprow);

      toprow.gridx = 1;
      toprow.gridy = 0;
      add(canvas, toprow);

      toprow.gridx = 2;
      toprow.gridy = 0;

      BasicArrowButton buttoneast = new BasicArrowButton(BasicArrowButton.EAST) {
        @Override public Dimension getPreferredSize() { return new Dimension(30, 350);}
      };

      add(buttoneast, toprow);

      toprow.fill = GridBagConstraints.NONE;
      toprow.gridx = 0;
      toprow.gridy = 1;
      toprow.insets = new Insets(0,30,0,0);
      toprow.gridwidth = 2;

      toprow.anchor = GridBagConstraints.WEST;
      add(inventory, toprow);

      toprow.gridx = 1;
      toprow.gridy = 1;
      toprow.insets = new Insets(0,0,0,30);
      toprow.anchor = GridBagConstraints.EAST;
      add(crafting, toprow);

      pack();
    }else{
      JOptionPane.showMessageDialog(this, "Game Load Error");
      exit(0);
    }

    getContentPane().setBackground(Color.gray);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
    startListening(gameWorld);
  }

  private void startListening(GameWorld gameWorld) throws InterruptedException {
    while(true){
      //inventory.updateInventoryGUI(gameWorld.getRoom().getContents()); //TODO: If Item is added or removed
      /*Map<GameWorld.Direction, ArrayList<WorldObject>> objectList = gameWorld.getRoom(0,0).getContents();
      System.out.println(Arrays.toString(objectList.get(GameWorld.Direction.NORTH).toArray()));*/

      Thread.sleep(4000);
    }
  }

  private GameWorld createGameWorld(File saveFile){
      return Persistence.XmlToObject(saveFile);
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


