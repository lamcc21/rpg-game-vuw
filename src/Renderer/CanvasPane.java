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
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;

import Application.ContainerPane;
import GameWorld.*;
import GameWorld.Container;

public class CanvasPane extends JPanel{
    /*object drawing fields*/

    //object sizes based on Z value
    private int MIN_SIZE = 25;
    private int MED_SIZE = 50;
    private int MAX_SIZE = 75;

    //object rows based on Z value
    private int BOTTOM_BACK_ROW = 410;
    private int BOTTOM_MID_ROW = 450;
    private int BOTTOM_FRONT_ROW = 520;
    private int CENTRE_ROW = 250;
    private int ROOF_BACK_ROW = 80;
    private int ROOF_MID_ROW = 35;
    private int ROOF_FRONT_ROW = 5;

    //object rows starting x pos based on Z value
    private int BACK_X_START = 170;
    private int MID_X_START = 110;
    private int FRONT_X_START = 40;

    //object rows respective spacing size based on Z value
    private int BACK_X_SPACING = 210;
    private int MID_X_SPACING = 260;
    private int FRONT_X_SPACING = 320;

    //bounding box for objects
    private HashMap<Rectangle, WorldObject> boundingBoxes = new HashMap<>();

    // color fields
    private Color BLUE = Color.blue;
    private Color RED = Color.red;
    private Color GREEN = Color.green;

    // helper fields
    private Room room;
    private Player player;
    private GameWorld.Direction perspective;
    private GameWorld gameWorld;
    private ContainerPane pane;

    public CanvasPane(GameWorld gameWorld, ContainerPane pane) {
        setPreferredSize(new Dimension(800, 600));
        this.player = gameWorld.getPlayer();
        this.room = gameWorld.getRoom(player.getX(), player.getY());
        this.addMouseListener(MyMouseListener());
        this.gameWorld = gameWorld;
        this.pane = pane;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boundingBoxes = new HashMap<>();
        room = gameWorld.getRoom(player.getX(),player.getY());
        Graphics2D g2d = (Graphics2D) g;
        constructPolygonGradientMap(g2d);
        drawObjectsInPerspective(g2d, gameWorld.getObjectsInView());
    }

    private void drawObjectsInPerspective(Graphics2D g2d, List<WorldObject> objectsInView) {
        if(objectsInView!=null) {
	        for(WorldObject object : objectsInView) drawBufferedImages(g2d, object);
        }
    }

    private MouseListener MyMouseListener(){
      return new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
          boolean containerclicked = false;
          try {
            //iterate through all bounding boxes
            for (Rectangle r : boundingBoxes.keySet()) {
              //if click is within a bounding box
              if (r.contains(e.getX(), e.getY())) {
                //initialise object associated with bounding box clicked
                WorldObject object = boundingBoxes.get(r);
                //check if container is clicked
                if (object instanceof Container) {
                  //Updates Container Inventory
                  pane.setActive(true);
                  pane.updateContainerGUI((Container) object, player);
                  containerclicked = true;
                } else if (object instanceof Holdable) {
                  //check if object is clicked
                  //pick up the object
                  gameWorld.pickUp(object);
                  //remove the associated object
                  boundingBoxes.remove(r);
                } else if (object instanceof Door) {
                  //check if door is clicked
                  //check if door is locked, moving rooms if not.
                  if (((Door) object).getIsLocked()) {
                    player.unlock(((Door) object)); //Tries to unlock door
                  }

                  if (!((Door) object).getIsLocked()) {
                    player.moveRoom(player.getPerspective());
                    //remove the door
                    boundingBoxes.remove(r);
                  } else JOptionPane.showMessageDialog(null, "Door Is Locked, You need to Make Key");
                }
                //update canvas
                repaint();
              }
            }
            if(!containerclicked){
            if(pane.isActive()){
                pane.updateContainerGUI();
                pane.setActive(false);
              }//Closes container panel
            }
          }catch(ConcurrentModificationException c){}
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

    private void drawBufferedImages(Graphics2D g2d, WorldObject object) {
        int startX;
        int spaceX;
        int x;
        int y;
        int z = object.getZ();
        int objectHeight = object.getY();
        int objectSize;

        if(z == 2){
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
        	File image = new File(object.getFilePath());
            BufferedImage img = ImageIO.read(image);

            //draw
            g2d.drawImage(img, x, y, objectSize, objectSize, null);
            //add bounding box to the arraylist to allow for detectable clicks`
            Rectangle bound = new Rectangle(x, y, objectSize, objectSize);
            boundingBoxes.put(bound, object);
        } catch (IOException e) {
            System.out.println(object.getFilePath());
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
        g2d.drawLine(0, 100, 800, 100); //top angle
        g2d.drawLine(0, 400, 800, 400); //bottom angle
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

        this.perspective = player.getPerspective();
        if (room.getWall(perspective).hasDoor()) {
            Door door = room.getWall(perspective).getDoor();
            Color backDoorColor = new Color(door.getGameColor().getR(), door.getGameColor().getG(), door.getGameColor().getB());
            g2d.setColor(backDoorColor);
            g2d.fillRect(350, 225, 100, 175);
            g2d.setColor(Color.black);
            g2d.drawRect(350, 225, 100, 175);
            g2d.fillOval(430, 325, 10, 10); //back knob
            Rectangle bound = new Rectangle(350, 225, 100, 175);
            boundingBoxes.put(bound, door);
        }
    }


}

