package Renderer;

/**
 * For SWEN225 Group Project - Lachlan Mears
 */

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import GameWorld.*;

public class CanvasPane extends JPanel {

    // color fields
    private Color BLUE = Color.blue;
    private Color RED = Color.red;
    private Color GREEN = Color.green;

    private GameWorld gameWorld;
    GameWorld.Direction perspective;

    public CanvasPane(GameWorld gameWorld) {
        setPreferredSize(new Dimension(800, 600));
        this.gameWorld = gameWorld;
        //perspective = GameWorld.getPlayer().getPerspective();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawRoom(g2d, g);
        constructPolygonGradientMap(g2d);
    }

    public void drawRoom(Graphics2D g2d, Graphics g) {
        drawSurfaces(g);
        drawObjects(g);
    }

    private void constructPolygonGradientMap(Graphics2D g2d){

        HashMap<Polygon, GradientPaint> polygonGradientMap = new HashMap<>();

        Color foregroundColor = new Color(220, 255, 255);
        Color backgroundColor = Color.WHITE;

        //construct floor polygon and gradient
        int[] floorX = {0, 0, 150, 650, 800, 800};
        int[] floorY = {600, 500, 400, 400, 500, 600};
        Polygon floor = new Polygon(floorX, floorY, 6);

        GradientPaint floorGradient = new GradientPaint(0, 500, foregroundColor, 0, 400, backgroundColor);
        polygonGradientMap.put(floor, floorGradient);

        //construct left wall polygon and gradient
        int[] leftWallX = {0, 150, 150, 0};
        int[] leftWallY = {0, 100, 400, 500};
        Polygon leftWall = new Polygon(leftWallX, leftWallY, 4);

        GradientPaint leftWallGradient = new GradientPaint(0, 0, foregroundColor, 150, 0, backgroundColor);
        polygonGradientMap.put(leftWall, leftWallGradient);

        //construct roof polygon and gradient
        int[] roofX = {0, 150, 650, 800};
        int[] roofY = {0, 100, 100, 0};
        Polygon roof = new Polygon(roofX, roofY, 4);

        GradientPaint roofGradient = new GradientPaint(0, 0, foregroundColor, 0, 100, backgroundColor);
        polygonGradientMap.put(roof, roofGradient);

        //construct right wall polygon and gradient
        int[] rightWallX = {800, 650, 650, 800};
        int[] rightWallY = {0, 100, 400, 500};
        Polygon rightWall = new Polygon(rightWallX, rightWallY, 4);

        GradientPaint rightWallGradient = new GradientPaint(800, 0, foregroundColor, 650, 0, backgroundColor);
        polygonGradientMap.put(rightWall, rightWallGradient);

        //construct back wall polygon and gradient
        int[] backWallX = {150, 650, 650, 150};
        int[] backWallY = {100, 100, 400, 400};
        Polygon backWall = new Polygon(backWallX, backWallY, 4);

        GradientPaint backWallGradient = new GradientPaint(0, 100, backgroundColor, 0, 400, backgroundColor);
        polygonGradientMap.put(backWall, backWallGradient);

        //draw surfaces and angles
        fillPolygons(g2d, polygonGradientMap);
        drawDoor(g2d);
        drawAngles(g2d);
    }

    private void drawAngles(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.drawRect(150, 100, 500, 300); //back wall
        g2d.drawLine(0, 0, 150, 100); //top left angle
        g2d.drawLine(0, 500, 150, 400); //bottom left angle
        g2d.drawLine(800, 0, 650, 100); //top right angle
        g2d.drawLine(800, 500, 650, 400); //bottom right angle
    }

    private void fillPolygons(Graphics2D g2d, HashMap<Polygon, GradientPaint> polygons){
        for(Polygon p : polygons.keySet()){
            g2d.setPaint(polygons.get(p));
            g2d.fillPolygon(p);
        }
    }

    private void drawDoor(Graphics2D g2d){
        //construct door polygon and gradient
        int[] doorX = {350, 450, 450, 350};
        int[] doorY = {225, 225, 400, 400};
        Polygon door = new Polygon(doorX, doorY, 4);
        Color gold = new Color( 193, 184, 95);
        g2d.setColor(gold);
        g2d.fillPolygon(door);
        g2d.setColor(Color.black);
        g2d.drawPolygon(door);
        g2d.fillOval(430,325, 10, 10);

    }

    private void drawSurfaces(Graphics g) {
        /*Wall backWall = null;
        switch (perspective) {
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
        if (backWall.isVisible()) {
          //draw wall
        }*/
    }

    private void drawObjects(Graphics g) {
        /*for(WorldObject object: gameWorld.getRoom()){
          drawObject(object);
        }*/
    }

    private void drawObject(WorldObject object) {
        /*int objectDistance = object.getDistance();
        if(object.isVisible()) {
          //draw object
        }*/
    }
}