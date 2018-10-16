package Application;
import GameWorld.GameWorld;
import Persistence.Persistence;
import Renderer.CanvasPane;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

/**
 * For SWEN225 Group Project - Lachlan McCrae
 */

public class Application extends JFrame{
  private CanvasPane canvas;
  private InventoryPane inventory;
  private CraftingPane crafting;
  private ContainerPane container;
  private GameWorld gameWorld;

  private Application() throws IOException, InterruptedException {
    super("Have A Go Escaping");
    setLayout(new GridBagLayout());new Dimension();
    setMinimumSize(new Dimension(860, 802));
    UIManager.put("ToolTip.background", new Color(67, 125, 128));
    setUIFont(new javax.swing.plaf.FontUIResource("Futuro", Font.BOLD, 15));
    this.gameWorld = createGameWorld(new File("prototypeGame1.xml"));

    if(gameWorld != null){
      this.container = new ContainerPane();
      this.canvas = new CanvasPane(gameWorld,container);
      this.inventory = new InventoryPane(gameWorld);
      this.crafting = new CraftingPane(gameWorld);
      GridBagConstraints gbc = new GridBagConstraints();

      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
      //This centers every window displayed


      BasicArrowButton buttonWest = new BasicArrowButton(BasicArrowButton.WEST) {
        @Override public Dimension getPreferredSize() { return new Dimension(30, 350);}
      };

      buttonWest.addActionListener(e -> {
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getLeft());
        System.out.println(gameWorld.getPlayer().getPerspective());
        canvas.revalidate();
        canvas.repaint();
      });

      gbc.anchor = GridBagConstraints.NORTH;
      gbc.fill = GridBagConstraints.VERTICAL;
      gbc.gridx = 0;
      gbc.gridwidth = 3;
      gbc.gridy = 0;
      add(container, gbc);

      gbc.anchor = GridBagConstraints.SOUTH;
      gbc.gridwidth = 1;
      gbc.gridx = 0;
      gbc.gridy = 1;
      add(buttonWest, gbc);

      gbc.gridx = 1;
      gbc.gridy = 1;
      add(canvas, gbc);

      BasicArrowButton buttonEast = new BasicArrowButton(BasicArrowButton.EAST) {
        @Override public Dimension getPreferredSize() { return new Dimension(30, 350);}
      };

      buttonEast.addActionListener(e -> {
        gameWorld.getPlayer().setPerspective(gameWorld.getPlayer().getRight());
        System.out.println(gameWorld.getPlayer().getPerspective());
        canvas.revalidate();
        canvas.repaint();
      });

      gbc.gridx = 2;
      gbc.gridy = 1;
      add(buttonEast, gbc);

      gbc.fill = GridBagConstraints.NONE;
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.gridwidth = 2;
      gbc.anchor = GridBagConstraints.WEST;
      add(inventory, gbc);

      gbc.gridx = 1;
      gbc.gridy = 2;
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

  private void createSaveFile(GameWorld gameWorld){Persistence.ObjectToXml(gameWorld, "prototypeGame");}

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


