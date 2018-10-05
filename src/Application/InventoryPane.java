package Application;

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
  private JButton[] inventoryItems;

  InventoryPane(List<? extends WorldObject> inventory) throws IOException {
    inventoryItems = new JButton[5];
    setLayout(new GridLayout(1,10));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.EAST;

    setBackground(Color.DARK_GRAY);
    setPreferredSize(new Dimension(500,50));
    //addInventoryGUI(inventory);

    /*for(JButton aGameGrid: inventoryItems){
        add(aGameGrid);
    }*/
  }

  private void addInventoryGUI(List<? extends WorldObject> inventory){ //TODO: bug fix this
    boolean noMoreItems = false;
    assert inventory != null;
    if(inventory == null){System.out.println("nothing to show");}

    for(int i=0; i<inventoryItems.length;){
      if(!noMoreItems){
        if(inventory.get(i) == null) {
          noMoreItems = true;
        }else {
          System.out.println("added");
          WorldObject item = inventory.get(i);
          inventoryItems[i]= new JButton();
          inventoryItems[i].setIcon(new ImageIcon()); //TODO: get correct image for Icon in room contents
          inventoryItems[i].setToolTipText(item.getName() + ": " +item.getDescription() + "\nClick To Drop");
          inventoryItems[i].addMouseListener(MyMouseListener());
          //TODO: need to figure out how to remove object from list in Room
          //TODO: could compare Inventory list in Application and one in InventoryPane get
          inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
          i++;
        }
      }else{
        inventoryItems[i]= new JButton();
        inventoryItems[i].setToolTipText("Empty Slot");
        inventoryItems[i].setIcon(new ImageIcon(defaultImage));//TODO: Icon shit will be added from xml
        inventoryItems[i].setEnabled(false);
        i++;
      }
    }
  }

  public void updateInventoryGUI(List<? extends WorldObject> inventory){
    boolean noMoreItems = false;
    if(inventory.isEmpty()){noMoreItems=true;}

    for(int i=0; i<inventoryItems.length;){
      if(!noMoreItems){
        if(inventory.get(i) != null){
          WorldObject item = inventory.get(i);
          inventoryItems[i].setIcon(new ImageIcon());
          inventoryItems[i].setToolTipText(item.getName() + ": " + item.getDescription() + "\nClick To Drop");
          inventoryItems[i].addMouseListener(MyMouseListener());
          inventoryItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
          i++;
        }
        else{noMoreItems=true;}
      }else{
        inventoryItems[i].setIcon(new ImageIcon(defaultImage));//TODO: Icon shit will be added from xml
        inventoryItems[i].setToolTipText("Empty Slot");
        inventoryItems[i].setEnabled(false);
        i++;
      }
    }
    this.revalidate();
  }

  private MouseListener MyMouseListener(){
    return new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e1) {
        //TODO: This Method Just needs to call a drop method in room
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
