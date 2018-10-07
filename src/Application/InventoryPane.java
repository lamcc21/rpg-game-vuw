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
  private final BufferedImage keyImage = ImageIO.read(getClass().getResource("key.png"));
  //Icon made on flaticon.com and is licensed by http://creativecommons.org/licenses/by/3.0/
  //TODO: Find a cyan key icon
  //TODO: Find a purple key icon
  //TODO: Find a dark green key icon
  //TODO: Find a gold key icon
  //TODO: Find a brown key icon
  //TODO: find a silver key icon
  private JButton[] inventoryItems;
  private JButton[] keyItems;

  InventoryPane(GameWorld gameWorld) throws IOException {
    inventoryItems = new JButton[16];
    keyItems = new JButton[6];

    setBackground(Color.DARK_GRAY);
    setPreferredSize(new Dimension(500,150));

    setLayout(new GridLayout(2,11));
    ToolTipManager.sharedInstance().setInitialDelay(200);
    GridBagConstraints gbc = new GridBagConstraints();

    addInventoryGUI(gameWorld.getPlayer().getInventory(), gameWorld.getPlayer());
    addCraftGUI();

    for (JButton inventoryItem : inventoryItems) {add(inventoryItem, gbc);}
    for(JButton craftButton: keyItems){add(craftButton, gbc); }
  }

  private void addCraftGUI(){
    for(int j=0; j<keyItems.length; j++){
      keyItems[j]= new JButton();
      keyItems[j].setEnabled(false);
    }

    keyItems[0].setBackground(Color.cyan);
    keyItems[0].setIcon(new ImageIcon(keyImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
    keyItems[0].setToolTipText("Craft Cyan Key");
    keyItems[0].setEnabled(true);

    keyItems[1].setBackground(Color.magenta);
    keyItems[1].setIcon(new ImageIcon(keyImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
    keyItems[1].setToolTipText("Craft Purple Key");

    keyItems[2].setBackground(Color.green);
    keyItems[2].setIcon(new ImageIcon(keyImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
    keyItems[2].setToolTipText("Craft Green Key");

    keyItems[3].setBackground(Color.yellow);
    keyItems[3].setIcon(new ImageIcon(keyImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
    keyItems[3].setToolTipText("Craft Gold Key");

    keyItems[4].setBackground(Color.blue);
    keyItems[4].setIcon(new ImageIcon(keyImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
    keyItems[4].setToolTipText("Craft Brown Key");

    keyItems[5].setBackground(Color.DARK_GRAY);
    keyItems[5].setIcon(new ImageIcon(keyImage.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH)));
    keyItems[5].setToolTipText("Craft Silver Key");
  }

  private void updateCraftGUI(GameWorld gameWorld){
    for(int j=0; j<keyItems.length; j++){
      keyItems[j].setEnabled(false);
    }
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
        inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      }else{
        i=inventory.size();
      }
    }

    for(int j=inventory.size(); j<inventoryItems.length; j++){
      inventoryItems[j]= new JButton();
      inventoryItems[j].setToolTipText("Empty Slot");
      inventoryItems[j].setIcon(new ImageIcon(defaultImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
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
        inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        inventoryItems[i].setEnabled(true);
      } else {
        i = inventory.size();
      }
    }

    for(int j=inventory.size(); j<inventoryItems.length; j++){
      inventoryItems[j].setToolTipText("Empty Slot");
      inventoryItems[j].setIcon(new ImageIcon(defaultImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
      inventoryItems[j].setEnabled(false);
    }
  }


  private String getText(String item, String description){
    return "<html>" +
        "<p>" +
        item +
        "<br>" + description +
        "<br>" + "Click To Drop" +
        "</p>\n" +
        "<table>\n";
  }

}
