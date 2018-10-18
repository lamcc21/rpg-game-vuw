package Application;

import GameWorld.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

class InventoryPane extends JPanel{
  private final BufferedImage defaultImage = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage keyImage = ImageIO.read(getClass().getResource("key.png"));
  //Icon made on flaticon.com and is licensed by http://creativecommons.org/licenses/by/3.0/
  private JButton[] inventoryItems;

  InventoryPane(GameWorld gameWorld) throws IOException {
    inventoryItems = new JButton[16];

    setBackground(Color.darkGray);
    setPreferredSize(new Dimension(560,140));

    setLayout(new GridLayout(2,8));
    ToolTipManager.sharedInstance().setInitialDelay(400);
    ToolTipManager.sharedInstance().setReshowDelay(400);
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
        inventoryItems[i].setIcon(new ImageIcon(getIcon(item).getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
        if(item instanceof KeyObject){ inventoryItems[i].setBackground(convertColourClass(item.getGameColor()));}
        inventoryItems[i].setToolTipText(getText(item.getName(), item.getDescription()));
        inventoryItems[i].addActionListener((e -> {
          player.dropItem(item);
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
      inventoryItems[j].setToolTipText("Empty Slot");
      inventoryItems[j].setBackground(new Color(255,255,255));
      inventoryItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      inventoryItems[j].setEnabled(false);
    }
  }

  public void updateInventoryGUI(List<? extends WorldObject> inventory, Player player) {
    assert inventory != null;
    for (int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i) != null) {
        WorldObject item = inventory.get(i);
        inventoryItems[i].setIcon(new ImageIcon(getIcon(item).getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
        if(item instanceof KeyObject){ inventoryItems[i].setBackground(convertColourClass(item.getGameColor()));}
        inventoryItems[i].setToolTipText(getText(item.getName(), item.getDescription()));
        inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inventoryItems[i].setEnabled(true);
      } else {
        i = inventory.size();
      }
    }

    for(int j=inventory.size(); j<inventoryItems.length; j++){
      inventoryItems[j].setIcon(new ImageIcon(defaultImage.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH)));
      inventoryItems[j].setBackground(new Color(255,255,255));
      inventoryItems[j].setToolTipText("Empty Slot");
      inventoryItems[j].setEnabled(false);
      inventoryItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }


  private BufferedImage getIcon(WorldObject item) {
    if(item instanceof KeyObject){return this.keyImage;}
    else{
      File image = new File(item.getFilePath());
      try {
        return ImageIO.read(image);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return defaultImage;
  }

  //This is only needed because swing can't handle custom colour class;
  private Color convertColourClass(GameColor color){
    if(color.equals(GameColor.cyan)){return new Color(67,125,128);}
    else if(color.equals(GameColor.purple)){return new Color(75, 66, 121);}
    else if(color.equals(GameColor.green)){return new Color(63,99, 37);}
    else if(color.equals(GameColor.gold)){return new Color(170, 170, 36);}
    else if(color.equals(GameColor.brown)){return new Color(114,50,28);}
    else if(color.equals(GameColor.silver)){return new Color(121,121,121);}
    return Color.black;
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
