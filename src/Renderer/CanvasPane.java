package Renderer;

/**
 * For SWEN225 Group Project - Lachlan Mears
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import GameWorld.*;

public class CanvasPane extends JPanel{
    /*object drawing fields*/

    //object sizes
    private int MIN_SIZE = 25;
    private int MED_SIZE = 50;
    private int MAX_SIZE = 75;

    //object rows
    private int BACK_ROW = 410;
    private int MID_ROW = 450;
    private int FRONT_ROW = 520;

    //object rows starting x pos
    private int BACK_X_START = 170;
    private int MID_X_START = 110;
    private int FRONT_X_START = 40;

    //object rows respective spacing size
    private int BACK_X_SPACING = 210;
    private int MID_X_SPACING = 260;
    private int FRONT_X_SPACING = 320;

    private ArrayList<Rectangle> boundingBoxes = new ArrayList<>();

    // color fields
    private Color BLUE = Color.blue;
    private Color RED = Color.red;
    private Color GREEN = Color.green;

    // helper fields
    private Room room;
    private Player player;
    private GameWorld.Direction perspective;

    public CanvasPane(GameWorld gameWorld) {
        setPreferredSize(new Dimension(800, 600));
        this.player = gameWorld.getPlayer();
        //this.room = gameWorld.getRoom(player.getX(), player.getY());
        this.addMouseListener(MyMouseListener());

      //this.perspective = player.getPerspective();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawRoom(g2d, g);
        constructPolygonGradientMap(g2d);
        //drawBufferedImages(g2d);
    }

    private void drawBufferedImages(Graphics2D g2d) {
        //test method for drawing 2d box png in 3d
        try {

            BufferedImage img = ImageIO.read(new File("/home/mearslach/Desktop/SWEN225/SWEN225-Group-Project/src/Renderer/Sprites/Crate.png"));

            //draw back row
            for(int x = BACK_X_START; x <= (BACK_X_START+BACK_X_SPACING)*3; x += BACK_X_SPACING) {
                g2d.drawImage(img, x, BACK_ROW, MIN_SIZE, MIN_SIZE, null);
                //add bounding box to the arraylist to allow for detectable clicks
                Rectangle bound = new Rectangle(x, BACK_ROW, MIN_SIZE, MIN_SIZE);
                boundingBoxes.add(bound);
            }

            //draw mid row
            for(int x = MID_X_START; x <= (MID_X_START+MID_X_SPACING)*3; x += MID_X_SPACING) {
                g2d.drawImage(img, x, MID_ROW, MED_SIZE, MED_SIZE, null);
                //add bounding box to the arraylist to allow for detectable clicks
                Rectangle bound = new Rectangle(x, MID_ROW, MED_SIZE, MED_SIZE);
                boundingBoxes.add(bound);
            }

            //draw front row
            for(int x = FRONT_X_START; x <= (FRONT_X_START+FRONT_X_SPACING)*3; x += FRONT_X_SPACING) {
                g2d.drawImage(img, x, FRONT_ROW, MAX_SIZE, MAX_SIZE, null);
                //add bounding box to the arraylist to allow for detectable clicks
                Rectangle bound = new Rectangle(x, FRONT_ROW, MAX_SIZE, MAX_SIZE);
                boundingBoxes.add(bound);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawRoom(Graphics2D g2d, Graphics g) {
        drawSurfaces(g);
        //drawObjects(g);
    }

    private void constructPolygonGradientMap(Graphics2D g2d){

        HashMap<Polygon, GradientPaint> polygonGradientMap = new HashMap<>();

        //set foreground / background colors (instead of hardcoding this, change it to get the color from GameWorld)
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
        //drawDoors(g2d, room);
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

    private void drawDoors(Graphics2D g2d, Room room){
        //construct door polygon and gradient

        //set respective door colors (instead of hardcoding this, get the color from GameWorld)

        //left door
        if (room.getWall(player.getLeft()).hasDoor()) {
            Color leftDoorColor = new Color(room.getWall(player.getRight()).getDoor().getColor().getR(), room.getWall(player.getRight()).getDoor().getColor().getG(), room.getWall(player.getRight()).getDoor().getColor().getB());
            int[] leftDoorX = {50, 100, 100, 50};
            int[] leftDoorY = {466, 433, 225, 208};
            Polygon leftDoor = new Polygon(leftDoorX, leftDoorY, 4);
            g2d.setColor(leftDoorColor);
            g2d.fillPolygon(leftDoor);
            g2d.setColor(Color.black);
            g2d.drawPolygon(leftDoor);
            g2d.fillOval(85, 350, 8, 10); //left knob
        }

        //back door
        if (room.getWall(perspective).hasDoor()) {
            Color backDoorColor = new Color(room.getWall(player.getRight()).getDoor().getColor().getR(), room.getWall(player.getRight()).getDoor().getColor().getG(), room.getWall(player.getRight()).getDoor().getColor().getB());
            int[] backDoorX = {350, 450, 450, 350};
            int[] backDoorY = {225, 225, 400, 400};
            Polygon backDoor = new Polygon(backDoorX, backDoorY, 4);
            g2d.setColor(backDoorColor);
            g2d.fillPolygon(backDoor);
            g2d.setColor(Color.black);
            g2d.drawPolygon(backDoor);
            g2d.fillOval(430, 325, 10, 10); //back knob
        }

        //right door
        if (room.getWall(player.getRight()).hasDoor()) {
            Color rightDoorColor = new Color(room.getWall(player.getRight()).getDoor().getColor().getR(), room.getWall(player.getRight()).getDoor().getColor().getG(), room.getWall(player.getRight()).getDoor().getColor().getB());
            int[] rightDoorX = {700, 750, 750, 700};
            int[] rightDoorY = {433, 466, 208, 225};
            Polygon rightDoor = new Polygon(rightDoorX, rightDoorY, 4);
            g2d.setColor(rightDoorColor);
            g2d.fillPolygon(rightDoor);
            g2d.setColor(Color.black);
            g2d.drawPolygon(rightDoor);
            g2d.fillOval(735, 350, 8, 10); //right knob
        }
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

    /*private void drawObjects(Graphics g) {
        for(WorldObject object: GameWorld.getRoom().getContents()){
          drawObject(object);
        }
    }*/

    private void drawObject(WorldObject object) {
        /*int objectDistance = object.getDistance();
        if(object.isVisible()) {
          //draw object
        }*/
    }

    private MouseListener MyMouseListener(){
      return new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
          for(Rectangle r : boundingBoxes){
            if(r.contains(e.getX(), e.getY())){
              System.out.println("Box click detected");
            }
          }
          //TODO: Used for picking up object if possible otherwise player will be notified that it is unobtainable??
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
          //TODO: I think this can be used for highlighting description
        }

        @Override
        public void mouseExited(MouseEvent e) {}
      };
    }
}