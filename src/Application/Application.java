package Application;
import Renderer.CanvasPane;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * For SWEN225 Group Project - Lachlan McCrae
 */

public class Application extends JFrame{
  private boolean gameStarted;
  private final CanvasPane canvas;
  private final InventoryPane inventory;

  private Application() throws IOException {
    super("UNNAMED GAME");
    setLayout(new BorderLayout());
    setResizable(false);
    setUIFont(new javax.swing.plaf.FontUIResource("Futuro",Font.BOLD,15));
    this.canvas = new CanvasPane();
    this.inventory = new InventoryPane();

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    //This centers every window displayed

    getContentPane().add(canvas, BorderLayout.CENTER);
    getContentPane().add(inventory, BorderLayout.SOUTH);

    pack();
    setLocationRelativeTo(null);
    setVisible(true);

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
