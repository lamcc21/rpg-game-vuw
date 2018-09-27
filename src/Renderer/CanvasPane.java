package Renderer;

import javax.swing.*;
import java.awt.*;
import GameWorld.*;

import static GameWorld.GameWorld.Direction.*

public class CanvasPane extends JPanel {
  private Color color = Color.darkGray;
  private GameWorld gameWorld;
  GameWorld.Direction perspective;

  public CanvasPane(GameWorld gameWorld) {
    setSize(800,800);
    this.gameWorld = gameWorld;
    perspective =  = GameWorld.getPlayer().getPerspective();
    //TODO: rendering of canvas
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D drawImage = (Graphics2D) g;
    if (color != null) {
      drawImage.setBackground(color);
      drawImage.drawRect(0, 0, 500, 500);
      drawRoom(g);
    }
  }

  public void drawRoom(Graphics g){
      drawSurfaces(g);
      drawObjects(g);
  }

  public void drawSurfaces(Graphics g){
      Wall backWall = null;
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
      }
  }

  public void drawObjects(Graphics g){
      for(WorldObject object: gameWorld.getRoom()){
        drawObject(object);
      }
  }

  public void drawObject(WorldObject object){
      int objectDistance = object.getDistance();
      if(object.isVisible()) {
          //draw object
      }
  }
}
