package Application;
import GameWorld.GameWorld;
import Persistence.Persistence;
import Renderer.CanvasPane;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.System.exit;

/**
 * For SWEN225 Group Project - Lachlan McCrae
 */

public class Application extends JFrame{
  private CanvasPane canvas;
  private InventoryPane inventory;
  private CraftingPane crafting;
  private GameWorld gameWorld;


  private Application() throws IOException, InterruptedException {
    super("Have A Go Escaping");
    setLayout(new GridBagLayout());new Dimension();
    setMinimumSize(new Dimension(860, 762));
    UIManager.put("ToolTip.background", new Color(67, 125, 128));
    setUIFont(new javax.swing.plaf.FontUIResource("Futuro", Font.BOLD, 15));
    this.gameWorld = createGameWorld(new File("prototypeGame1.xml"));

    if(gameWorld != null){
      this.canvas = new CanvasPane(gameWorld);
      this.inventory = new InventoryPane(gameWorld);
      this.crafting = new CraftingPane(gameWorld);
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.anchor = GridBagConstraints.NORTH;
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
      //This centers every window displayed

      gbc.fill = GridBagConstraints.VERTICAL;
      gbc.gridx = 0;
      gbc.gridy = 0;

      BasicArrowButton buttonWest = new BasicArrowButton(BasicArrowButton.WEST) {
        @Override public Dimension getPreferredSize() { return new Dimension(30, 350);}
      };

      buttonWest.addActionListener(e -> {
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getLeft());
        System.out.println(gameWorld.getPlayer().getPerspective());
        canvas.revalidate();
        canvas.repaint();
      });

      add(buttonWest, gbc);

      gbc.gridx = 1;
      gbc.gridy = 0;
      add(canvas, gbc);

      gbc.gridx = 2;
      gbc.gridy = 0;

      BasicArrowButton buttonEast = new BasicArrowButton(BasicArrowButton.EAST) {
        @Override public Dimension getPreferredSize() { return new Dimension(30, 350);}
      };

      buttonEast.addActionListener(e -> {
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getRight());
        System.out.println(gameWorld.getPlayer().getPerspective());
        canvas.revalidate();
        canvas.repaint();
      });

      add(buttonEast, gbc);

      gbc.fill = GridBagConstraints.NONE;
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 2;

      gbc.anchor = GridBagConstraints.WEST;
      add(inventory, gbc);

      gbc.gridx = 1;
      gbc.gridy = 1;
      gbc.anchor = GridBagConstraints.EAST;
      add(crafting, gbc);

      pack();
    }else{
      JOptionPane.showMessageDialog(this, "Game Load Error");
      exit(0);
    }

    getContentPane().setBackground(Color.gray);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);

    startListening();
  }


  private void startListening() throws InterruptedException {
    while(true){
        inventory.updateInventoryGUI(gameWorld.getPlayer().getInventory(), gameWorld.getPlayer());
        if(gameWorld.getPlayer().craftGuiUpdateNeeded()){
          crafting.updateCraftGUI(gameWorld);
          gameWorld.getPlayer().toggleUpdateNeeded(); //stopped the craft gui from updating till another item is added
        }
        Thread.sleep(1000);
        //TODO: need to update craft gui when ever a item is added
    }
  }

  private void createSaveFile(GameWorld gameWorld){
    try {
      Persistence.ObjectToXml(gameWorld, "prototypeGame");
    } catch (JAXBException e) {
      //TODO: handle save error
    } catch (FileNotFoundException e) {
      //TODO: handle save error
    }
  }

  private GameWorld createGameWorld(File saveFile){
    try {
      return Persistence.XmlToObject(saveFile);
    } catch (JAXBException e) {
      //TODO: handle load error
    }
    return null;
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


