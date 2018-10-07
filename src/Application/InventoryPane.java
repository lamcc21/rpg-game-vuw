package Application;

import GameWorld.GameWorld;
import GameWorld.Player;
import GameWorld.WorldObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;


class InventoryPane extends JPanel{
  private final BufferedImage defaultImage = ImageIO.read(getClass().getResource("black.png"));
  //TODO: Find a purple key icon
  //TODO: Find a cyan key icon
  //TODO: Find a dark green key icon
  //TODO: Find a gold key icon
  private JButton[] inventoryItems;
  private JButton[] keyItems;

  InventoryPane(GameWorld gameWorld) throws IOException {
    inventoryItems = new JButton[16];
    setLayout(new GridLayout(2,8));
    ToolTipManager.sharedInstance().setInitialDelay(0);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.EAST;

    setBackground(Color.DARK_GRAY);
    setPreferredSize(new Dimension(500,80));
    addInventoryGUI(gameWorld);

    for(JButton aGameGrid: inventoryItems){
        add(aGameGrid);
    }
  }

  private void addInventoryGUI(GameWorld gameWorld){
    List<? extends WorldObject> inventory = gameWorld.getPlayer().getInventory();
    Player player = gameWorld.getPlayer();

    assert inventory != null;
    for(int i=0; i<inventory.size(); i++){
      if(inventory.get(i)!=null){
        WorldObject item = inventory.get(i);
        inventoryItems[i]= new JButton();
        inventoryItems[i].setIcon(new ImageIcon(defaultImage)); //TODO: get correct image for Icon in room contents
        inventoryItems[i].setToolTipText(item.getName() + ": " +item.getDescription() + "\nClick To Drop");
        inventoryItems[i].addActionListener((e -> {
          //TODO:need to make it so that it can only drop when container is active
          player.dropItem(item);
          updateInventoryGUI(gameWorld.getPlayer().getInventory(),gameWorld.getPlayer());
        }));
        inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      }else{
        i=inventory.size();
      }
      //TODO: need to compare Inventory list in Application to the one in this class to see if items have been dropped
      //TODO: and add those Items back into the container
    }

    for(int j=inventory.size(); j<inventoryItems.length; j++){
      inventoryItems[j]= new JButton();
      inventoryItems[j].setToolTipText("Empty Slot");
      inventoryItems[j].setIcon(new ImageIcon(defaultImage));
      inventoryItems[j].setEnabled(false);
    }
  }

  private void updateInventoryGUI(List<? extends WorldObject> inventory, Player player) {
    assert inventory != null;
    for (int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i) != null) {
        WorldObject item = inventory.get(i);
        inventoryItems[i].setIcon(new ImageIcon(defaultImage));
        inventoryItems[i].setToolTipText(item.getName() + ": " + item.getDescription() + "\nClick To Drop");
        inventoryItems[i].addActionListener(e -> player.dropItem(item));
        inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        inventoryItems[i].setEnabled(true);
      } else {
        i = inventory.size();
      }
    }

    for(int j=inventory.size(); j<inventoryItems.length; j++){
      inventoryItems[j].setToolTipText("Empty Slot");
      inventoryItems[j].setIcon(new ImageIcon(defaultImage));
      inventoryItems[j].setEnabled(false);
    }
  }




  //TODO: need functionality for creating keys
}
