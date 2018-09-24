package Renderer;

import javax.swing.*;
import java.awt.*;

public class CanvasPane extends JPanel {
  private Color color = Color.darkGray;

  public CanvasPane() {
    setSize(800,800);
    //TODO: rendering of canvas
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D drawImage = (Graphics2D) g;
    if (color != null) {
      drawImage.setBackground(color);
      drawImage.drawRect(0, 0, 500, 500);
    }
  }
}
