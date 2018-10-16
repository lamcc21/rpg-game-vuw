package Application;

import GameWorld.GameWorld;
import GameWorld.GameColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class CraftingPane extends JPanel{

    private final BufferedImage keyImage = ImageIO.read(getClass().getResource("key.png"));
    //Icon made on flaticon.com and is licensed by http://creativecommons.org/licenses/by/3.0/
    private JButton[] keyItems;

    CraftingPane(GameWorld gameWorld) throws IOException {
        keyItems = new JButton[6];
        setBackground(Color.darkGray);

        setLayout(new GridLayout(2,3));
        setPreferredSize(new Dimension(210,140));
        ToolTipManager.sharedInstance().setInitialDelay(200);
        GridBagConstraints gbc = new GridBagConstraints();

        addCraftGUI(gameWorld);
        updateCraftGUI(gameWorld);

        for(JButton craftButton: keyItems){add(craftButton, gbc); }
    }

    private void addCraftGUI(GameWorld gameWorld) {
        for(int j=0; j<keyItems.length; j++){
            keyItems[j]= new JButton();
            keyItems[j].setOpaque(true);
            keyItems[j].setIcon(new ImageIcon(keyImage.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH)));
            keyItems[j].setEnabled(false);
        }
        keyItems[0].setBackground(new Color(67,125,128));
        keyItems[0].addActionListener(e -> {
          gameWorld.getPlayer().craft(GameColor.cyan);
          keyItems[0].setEnabled(false);
        });

        keyItems[1].setBackground(new Color(75, 66, 121));
        keyItems[1].addActionListener((e -> {
          gameWorld.getPlayer().craft(GameColor.purple);
          keyItems[1].setEnabled(false);
        }));

        keyItems[2].setBackground(new Color(63,99, 37));
        keyItems[2].addActionListener((e -> {
          gameWorld.getPlayer().craft(GameColor.green);
          keyItems[2].setEnabled(false);
        }));

        keyItems[3].setBackground(new Color(170, 170, 36));
        keyItems[3].addActionListener((e -> {
          gameWorld.getPlayer().craft(GameColor.gold);
          keyItems[3].setEnabled(false);
        }));

        keyItems[4].setBackground(new Color(114,50,28));
        keyItems[4].addActionListener((e -> {
          gameWorld.getPlayer().craft(GameColor.brown);
          keyItems[4].setEnabled(false);
        }));

        keyItems[5].setBackground(new Color(121,121,121));
        keyItems[5].addActionListener((e -> {
          gameWorld.getPlayer().craft(GameColor.silver);
          keyItems[5].setEnabled(false);
        }));
    }

    public void updateCraftGUI(GameWorld gameWorld){
        for(int j=0; j<keyItems.length; j++){
            keyItems[j].setEnabled(false);
            switch (j) {
              case 0: if(gameWorld.getPlayer().isCraftable(new GameColor(67,125,128))){
                keyItems[j].setEnabled(true);
                keyItems[j].setToolTipText("Craft Cyan Key");
                keyItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
              }
              case 1: if(gameWorld.getPlayer().isCraftable(new GameColor(75, 66, 121))){
                keyItems[j].setEnabled(true);
                keyItems[j].setToolTipText("Craft Purple Key");
                keyItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
              }
              case 2: if(gameWorld.getPlayer().isCraftable(new GameColor(63,99, 37))){
                keyItems[j].setEnabled(true);
                keyItems[j].setToolTipText("Craft Green Key");
                keyItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
              }
              case 3: if(gameWorld.getPlayer().isCraftable(new GameColor(170, 170, 36))){
                keyItems[j].setEnabled(true);
                keyItems[j].setToolTipText("Craft Gold Key");
                keyItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
              }
              case 4: if(gameWorld.getPlayer().isCraftable(new GameColor(114,50,28))){
                keyItems[j].setEnabled(true);
                keyItems[j].setToolTipText("Craft Brown Key");
                keyItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
              }
              case 5: if(gameWorld.getPlayer().isCraftable(new GameColor(121,121,121))){
                keyItems[j].setEnabled(true);
                keyItems[j].setToolTipText("Craft Silver Key");
                keyItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
              }
          }
          if(!keyItems[j].isEnabled()){
            keyItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            keyItems[j].setToolTipText(null);
          }
        }
    }
}

