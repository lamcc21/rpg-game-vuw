package Application;


import GameWorld.GameWorld;
import GameWorld.WorldObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

class InventoryPane extends JPanel{
  private final BufferedImage RoneItemone = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage RoneItemtwo = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage RoneItemthree = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage firstkey = ImageIO.read(getClass().getResource("black.png"));

  private final BufferedImage RtwoItemone = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage RtwoItemtwo = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage RtwoItemthree = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage secondkey = ImageIO.read(getClass().getResource("black.png"));

  private final BufferedImage RthreeItemone = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage RthreeItemtwo = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage RthreeItemthree = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage threekey = ImageIO.read(getClass().getResource("black.png"));
  private final BufferedImage emptyslot = ImageIO.read(getClass().getResource("black.png"));

  private JLabel[] inventoryItems;

  InventoryPane(ArrayList<WorldObject> inventory) throws IOException {
    inventoryItems = new JLabel[10];
    setLayout(new GridLayout(1,10));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.EAST;

    setBackground(Color.DARK_GRAY);
    setPreferredSize(new Dimension(500,50));

    for(int i=0; i<inventoryItems.length; i++){
        inventoryItems[i]= new JLabel();
        inventoryItems[i].setIcon(new ImageIcon(firstkey));
        inventoryItems[i].addMouseListener(MyMouseListener());
        inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    for(JLabel aGameGrid: inventoryItems){
        add(aGameGrid);
    }
  }

  public void updateInventory(List items){
    for(int i=0; i<inventoryItems.length; i++){
      inventoryItems[i]= new JLabel();
      inventoryItems[i].setIcon(new ImageIcon(firstkey));
      inventoryItems[i].setToolTipText();
      inventoryItems[i].addMouseListener(MyMouseListener());
      inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
  }

  public BufferedImage getIcon(String itemName){
    switch(itemName){
      case "book" : return RoneItemone;
      case "tree" : return RoneItemthree;
      default: return emptyslot;
      //TODO: This is just an example
    }
  }

  public String getDescription(){
    return "";
  }

  private MouseListener MyMouseListener(){
    return new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e1) {

      }

      @Override
      public void mousePressed(MouseEvent e1) {}

      @Override
      public void mouseReleased(MouseEvent e1) {}

      @Override
      public void mouseEntered(MouseEvent e1) {}

      @Override
      public void mouseExited(MouseEvent e1) {}
    };
  }
}
