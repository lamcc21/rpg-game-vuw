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
import java.util.List;

import GameWorld.*;

public class CanvasPane extends JPanel{
    /*object drawing fields*/

    //object sizes
    private int MIN_SIZE = 25;
    private int MED_SIZE = 50;
    private int MAX_SIZE = 75;

    //object rows
    private int BOTTOM_BACK_ROW = 410;
    private int BOTTOM_MID_ROW = 450;
    private int BOTTOM_FRONT_ROW = 520;
    private int CENTRE_ROW = 250;
    private int ROOF_BACK_ROW = 80;
    private int ROOF_MID_ROW = 35;
    private int ROOF_FRONT_ROW = 5;

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
    private GameWorld gameWorld;

    public CanvasPane(GameWorld gameWorld) {
        setPreferredSize(new Dimension(800, 600));
        this.player = gameWorld.getPlayer();
        this.room = gameWorld.getRoom(player.getX(), player.getY());
        this.addMouseListener(MyMouseListener());
        this.perspective = player.getPerspective();
        this.gameWorld = gameWorld;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        constructPolygonGradientMap(g2d);
        drawObjectsInPerspective(g2d, gameWorld.getObjectsInView());
    }

    private void drawObjectsInPerspective(Graphics2D g2d, List<WorldObject> objectsInView) {
        for(WorldObject object : objectsInView) drawBufferedImages(g2d, object);
    }


    private void drawBufferedImages(Graphics2D g2d, WorldObject object) {
        int startX;
        int spaceX;
        int x;
        int y;
        int z = object.getZ();
        int objectHeight = object.getY();
        int objectSize;

        if(z == 0){
            startX = BACK_X_START;
            spaceX = BACK_X_SPACING;
            if(objectHeight == 0) y = BOTTOM_BACK_ROW;
            else if(objectHeight == 1) y = CENTRE_ROW;
            else y = ROOF_BACK_ROW;
            objectSize = MIN_SIZE;
        }
        else if(z == 1){
            startX = MID_X_START;
            spaceX = MID_X_SPACING;
            if(objectHeight == 0) y = BOTTOM_MID_ROW;
            else if(objectHeight == 1) y = CENTRE_ROW;
            else y = ROOF_MID_ROW;
            objectSize = MED_SIZE;
        }
        else{
            startX = FRONT_X_START;
            spaceX = FRONT_X_SPACING;
            if(objectHeight == 0) y = BOTTOM_FRONT_ROW;
            else if(objectHeight == 1) y = CENTRE_ROW;
            else y = ROOF_FRONT_ROW;
            objectSize = MAX_SIZE;
        }

        x = startX + object.getX()*spaceX;


        //test method for drawing 2d box png in 3d
        try {
            BufferedImage img = object.getImageFile();
            //draw
            g2d.drawImage(img, x, y, objectSize, objectSize, null);
            //add bounding box to the arraylist to allow for detectable clicks`
            Rectangle bound = new Rectangle(x, y, objectSize, objectSize);
            boundingBoxes.add(bound);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void constructPolygonGradientMap(Graphics2D g2d){

        HashMap<Polygon, GradientPaint> polygonGradientMap = new HashMap<>();

        //set foreground / background colors (instead of hardcoding this, change it to get the color from GameWorld)
        Color foregroundColor = new Color(182, 182, 255);
        Color backgroundColor = Color.WHITE;

        //construct floor polygon and gradient
        int[] floorX = {0, 0, 800, 800, };
        int[] floorY = {600, 400, 400, 600};
        Polygon floor = new Polygon(floorX, floorY, 4);

        GradientPaint floorGradient = new GradientPaint(0, 600, foregroundColor, 0, 400, backgroundColor);
        polygonGradientMap.put(floor, floorGradient);

        //construct roof polygon and gradient
        int[] roofX = {0, 0, 800, 800};
        int[] roofY = {0, 100, 100, 0};
        Polygon roof = new Polygon(roofX, roofY, 4);

        GradientPaint roofGradient = new GradientPaint(0, 0, foregroundColor, 0, 100, backgroundColor);
        polygonGradientMap.put(roof, roofGradient);

        //construct back wall polygon and gradient
        int[] backWallX = {0, 800, 800, 0};
        int[] backWallY = {100, 100, 400, 400};
        Polygon backWall = new Polygon(backWallX, backWallY, 4);

        GradientPaint backWallGradient = new GradientPaint(0, 100, backgroundColor, 0, 400, backgroundColor);
        polygonGradientMap.put(backWall, backWallGradient);

        //draw surfaces and angles
        fillPolygons(g2d, polygonGradientMap);
        drawDoors(g2d, room);
        drawAngles(g2d);
    }

    private void drawAngles(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, 100, 800, 100); //top left angle
        g2d.drawLine(0, 400, 800, 400); //bottom left angle
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

        if (room.getWall(perspective).hasDoor()) {
            Color backDoorColor = new Color(room.getWall(player.getRight()).getDoor().getColor().getR(), room.getWall(player.getRight()).getDoor().getColor().getG(), room.getWall(player.getRight()).getDoor().getColor().getB());
            //Color backDoorColor = room.getWall(perspective).getDoor().getColor();
            int[] backDoorX = {350, 450, 450, 350};
            int[] backDoorY = {225, 225, 400, 400};
            Polygon backDoor = new Polygon(backDoorX, backDoorY, 4);
            g2d.setColor(backDoorColor);
            g2d.fillPolygon(backDoor);
            g2d.setColor(Color.black);
            g2d.drawPolygon(backDoor);
            g2d.fillOval(430, 325, 10, 10); //back knob
        }
    }

    /*private void drawObjects(Graphics g) {
        for(WorldObject object: GameWorld.getRoom().getContents()){
          drawObject(object);
        }
    }*/

    /*private void drawObject(WorldObject object) {
        int objectDistance = object.getDistance();
        if(object.isVisible()) {
          //draw object
        }
    }*/

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