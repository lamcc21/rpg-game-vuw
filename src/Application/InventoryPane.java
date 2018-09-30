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

  private JLabel[][] inventoryItems;

  InventoryPane() throws IOException {
    inventoryItems = new JLabel[1][5];
    setLayout(new GridLayout(1,10));
    setBackground(Color.DARK_GRAY);
    setPreferredSize(new Dimension(500,50));

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
      public void mouseClicked(MouseEvent e1) {}

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
