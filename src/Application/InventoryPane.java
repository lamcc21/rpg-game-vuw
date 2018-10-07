package Application;

import GameWorld.GameWorld;
import GameWorld.Player;
import GameWorld.WorldObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

class InventoryPane extends JPanel{
  private final BufferedImage defaultImage = ImageIO.read(getClass().getResource("black.png"));
  //Icon made on flaticon.com and is licensed by http://creativecommons.org/licenses/by/3.0/
  //TODO: Find a cyan key icon
  //TODO: Find a purple key icon
  //TODO: Find a dark green key icon
  //TODO: Find a gold key icon
  //TODO: Find a brown key icon
  //TODO: find a silver key icon
  private JButton[] inventoryItems;

  InventoryPane(GameWorld gameWorld) throws IOException {
    inventoryItems = new JButton[16];

    setBackground(Color.darkGray);
    setPreferredSize(new Dimension(560,140));

    setLayout(new GridLayout(2,8));
    ToolTipManager.sharedInstance().setInitialDelay(200);
    GridBagConstraints gbc = new GridBagConstraints();

    addInventoryGUI(gameWorld.getPlayer().getInventory(), gameWorld.getPlayer());

    for (JButton inventoryItem : inventoryItems) {add(inventoryItem, gbc);}
  }

  private void addInventoryGUI(List<? extends WorldObject> inventory, Player player){
    assert inventory != null;
    for(int i=0; i<inventory.size(); i++){
      if(inventory.get(i)!=null){
        WorldObject item = inventory.get(i);
        inventoryItems[i]= new JButton();
        inventoryItems[i].setIcon(new ImageIcon(defaultImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
        inventoryItems[i].setToolTipText(getText(item.getName(), item.getDescription()));
        inventoryItems[i].addActionListener((e -> {
          //TODO:need to make it so that it can only drop when container is active
          player.dropItem(item);
          //inventory.add(item);
          updateInventoryGUI(player.getInventory(),player);
        }));
        inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      }else{
        i=inventory.size();
      }
    }

    for(int j=inventory.size(); j<inventoryItems.length; j++){
      inventoryItems[j]= new JButton();
      inventoryItems[j].setIcon(new ImageIcon(defaultImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
      inventoryItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      inventoryItems[j].setEnabled(false);
    }
  }

  private void updateInventoryGUI(List<? extends WorldObject> inventory, Player player) {
    assert inventory != null;
    for (int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i) != null) {
        WorldObject item = inventory.get(i);
        inventoryItems[i].setIcon(new ImageIcon(defaultImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
        inventoryItems[i].setToolTipText(getText(item.getName(), item.getDescription()));
        inventoryItems[i].addActionListener(e -> player.dropItem(item));
        inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inventoryItems[i].setEnabled(true);
      } else {
        i = inventory.size();
      }
    }

    for(int j=inventory.size(); j<inventoryItems.length; j++){
      inventoryItems[j].setIcon(new ImageIcon(defaultImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
      inventoryItems[j].setEnabled(false);
      inventoryItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  private String getText(String item, String description){
    return "<html>" +
        "<p>" +
        item +
        "<br>" + description +
        "<br>------------<br>" + "Click To Drop" +
        "</p>\n" +
        "<table>\n";
  }

}
