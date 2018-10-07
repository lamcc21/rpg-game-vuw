package Application;

import GameWorld.GameWorld;
import GameWorld.Player;
import GameWorld.WorldObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

class CraftingPane extends JPanel{
    private final BufferedImage keyImage = ImageIO.read(getClass().getResource("key.png"));
    //Icon made on flaticon.com and is licensed by http://creativecommons.org/licenses/by/3.0/
    //TODO: Find a cyan key icon
    //TODO: Find a purple key icon
    //TODO: Find a dark green key icon
    //TODO: Find a gold key icon
    //TODO: Find a brown key icon
    //TODO: find a silver key icon
    private JButton[] keyItems;

    CraftingPane(GameWorld gameWorld) throws IOException {
        keyItems = new JButton[6];
        setBackground(Color.darkGray);

        setLayout(new GridLayout(2,3));
        setPreferredSize(new Dimension(210,140));
        ToolTipManager.sharedInstance().setInitialDelay(200);
        GridBagConstraints gbc = new GridBagConstraints();

        addCraftGUI();
        for(JButton craftButton: keyItems){add(craftButton, gbc); }
    }

    private void addCraftGUI(){
        for(int j=0; j<keyItems.length; j++){
            keyItems[j]= new JButton();
            keyItems[j].setOpaque(true);

            keyItems[j].setEnabled(false);
        }

        keyItems[0].setBackground(new Color(67,125,128));
        keyItems[0].setForeground(new Color(67,125,128));
        keyItems[0].setIcon(new ImageIcon(keyImage.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH)));
        keyItems[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        keyItems[0].setToolTipText("Craft Cyan Key");
        keyItems[0].setEnabled(true);


        keyItems[1].setBackground(new Color(75, 66, 121));
        keyItems[1].setIcon(new ImageIcon(keyImage.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH)));
        //keyItems[1].setToolTipText("Craft Purple Key");
        keyItems[1].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        keyItems[2].setBackground(new Color(63,99, 37));
        keyItems[2].setIcon(new ImageIcon(keyImage.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH)));
        //keyItems[2].setToolTipText("Craft Green Key");
        keyItems[2].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        keyItems[3].setBackground(new Color(170, 170, 36));
        keyItems[3].setIcon(new ImageIcon(keyImage.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH)));
        //keyItems[3].setToolTipText("Craft Gold Key");
        keyItems[3].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        keyItems[4].setIcon(new ImageIcon(keyImage.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH)));
        keyItems[4].setBackground(Color.blue);
        // keyItems[4].setToolTipText("Craft Brown Key");
        keyItems[4].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        keyItems[5].setBackground(Color.DARK_GRAY);
        keyItems[5].setIcon(new ImageIcon(keyImage.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH)));
        //keyItems[5].setToolTipText("Craft Silver Key");
        keyItems[5].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private void updateCraftGUI(GameWorld gameWorld){
        for(int j=0; j<keyItems.length; j++){
            keyItems[j].setEnabled(false);
        }
    }

    private String getText(String item, String description){
        return "<html>" +
                "<p>" +
                item +
                "<br>" + description +
                "<br>------------<br>" + "Click To Drop" +
                "</p>\n" +
                "<table>\n";
    }

}

