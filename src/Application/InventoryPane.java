package Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

class InventoryPane extends JPanel{
  private BufferedImage RoneItemone = ImageIO.read(getClass().getResource("black.png"));
  private BufferedImage RoneItemtwo = ImageIO.read(getClass().getResource("black.png"));
  private BufferedImage RoneItemthree = ImageIO.read(getClass().getResource("black.png"));
  private BufferedImage firstkey = ImageIO.read(getClass().getResource("black.png"));

  private BufferedImage RtwoItemone = ImageIO.read(getClass().getResource("black.png"));
  private BufferedImage RtwoItemtwo = ImageIO.read(getClass().getResource("black.png"));
  private BufferedImage RtwoItemthree = ImageIO.read(getClass().getResource("black.png"));
  private BufferedImage secondkey = ImageIO.read(getClass().getResource("black.png"));

  private BufferedImage RthreeItemone = ImageIO.read(getClass().getResource("black.png"));
  private BufferedImage RthreeItemtwo = ImageIO.read(getClass().getResource("black.png"));
  private BufferedImage RthreeItemthree = ImageIO.read(getClass().getResource("black.png"));
  private BufferedImage threekey = ImageIO.read(getClass().getResource("black.png"));

  private JLabel[][] inventoryItems;

  InventoryPane() throws IOException {
    setSize(500,80);
    inventoryItems = new JLabel[1][5];
    setLayout(new GridLayout(1,10));

    for(int i=0; i<inventoryItems.length; i++){
      for(int j=0; j<inventoryItems[0].length; j++){
        inventoryItems[i][j]= new JLabel();
        inventoryItems[i][j].setIcon(new ImageIcon(firstkey));
        inventoryItems[i][j].addMouseListener(MyMouseListener());
        inventoryItems[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      }
    }

    for(JLabel[] aGameGrid: inventoryItems){
      for(int y = 0; y<inventoryItems[0].length; y++){
        add(aGameGrid[y]);
      }
    }
  }

  public void updateInventory(ArrayList items){}

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
