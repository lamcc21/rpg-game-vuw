package Renderer;

/**
 * For SWEN225 Group Project - Lachlan Mears
 */

import javax.swing.*;
import java.awt.*;
import GameWorld.*;

import static GameWorld.GameWorld.Direction.*;

public class CanvasPane extends JPanel {
  private GameWorld gameWorld;
  GameWorld.Direction perspective;

  public CanvasPane(GameWorld gameWorld) {
    setPreferredSize(new Dimension(800,600));
    this.gameWorld = gameWorld;
    //perspective = GameWorld.getPlayer().getPerspective();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);
    g2d.drawRect(300, 150, 200,140);
    g2d.drawLine(0,0,300,150);
    g2d.drawLine(0,600,300,290);
    g2d.drawLine(800,0,500,150);
    g2d.drawLine(800,600,500,290);
  }

  public void drawRoom(Graphics g){
    drawSurfaces(g);
    drawObjects(g);
  }

  private void drawSurfaces(Graphics g){
    /*Wall backWall = null;
    switch (perspective){
      case NORTH:
        backWall = Room.getWalls().get(SOUTH);
        break;
      case EAST:
        backWall = Room.getWalls().get(WEST);
        break;
      case SOUTH:
        backWall = Room.getWalls().get(NORTH);
        break;
      case WEST:
        backWall = Room.getWalls().get(EAST);
        break;
    }
    if(backWall.isVisible()){
      //draw wall
    }*/
  }

  private void drawObjects(Graphics g){
    /*for(WorldObject object: gameWorld.getRoom()){
      drawObject(object);
    }*/
  }

  private void drawObject(WorldObject object){
    /*int objectDistance = object.getDistance();
    if(object.isVisible()) {
      //draw object
    }*/
  }
}