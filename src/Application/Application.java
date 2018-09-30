package Application;
import GameWorld.GameWorld;
import Renderer.CanvasPane;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.io.IOException;

/**
 * For SWEN225 Group Project - Lachlan McCrae
 */

public class Application extends JFrame{
  private boolean gameStarted; //Will be used for start screen
  private final CanvasPane canvas;
  private final InventoryPane inventory;
  private final GameWorld gameWorld;

  private Application() throws IOException {
    super("Escape the Room");
    setLayout(new GridBagLayout());
    setResizable(false);
    setUIFont(new javax.swing.plaf.FontUIResource("Futuro",Font.BOLD,15));

    this.gameWorld = new GameWorld();
    this.canvas = new CanvasPane(gameWorld);
    this.inventory = new InventoryPane();

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.NORTH;

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    //This centers every window displayed

    gbc.gridx = 1;
    gbc.gridy = 0;
    add(canvas,gbc);

    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(new BasicArrowButton(BasicArrowButton.WEST, Color.GRAY, Color.BLACK, Color.BLACK, Color.BLACK),gbc);

    gbc.gridx = 2;
    gbc.gridy = 0;
    add(new BasicArrowButton(BasicArrowButton.EAST, Color.GRAY, Color.BLACK, Color.BLACK, Color.BLACK),gbc);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 1;
    gbc.gridy = 1;
    add(inventory,gbc);

    pack();

    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    startListening();
  }

  private void startListening() {
    //TODO: This method will will listen for updates and make changes To inventory/canvas with respective calls
  }

  public static void main(String[] args) throws IOException { new Application();}

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
