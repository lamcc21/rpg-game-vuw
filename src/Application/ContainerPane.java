package Application;

import GameWorld.Container;
import GameWorld.WorldObject;
import GameWorld.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ContainerPane extends JPanel {
  private JButton[] optionItems;
  private boolean isActive;

  public ContainerPane(){
    optionItems = new JButton[5];
    setLayout(new GridLayout(1,5));
    setPreferredSize(new Dimension(860,40));
    setBackground(Color.darkGray);
    addContainerGUI();
    for(JButton containerItems: optionItems){add(containerItems); }
  }

  private void addContainerGUI(){
    for(int j=0; j<optionItems.length; j++){
      optionItems[j]= new JButton();
      optionItems[j].setOpaque(true);
      optionItems[j].setEnabled(false);
    }
  }

  public void updateContainerGUI(Container inventory, Player player) {
	updateContainerGUI();
    for (int i = 0; i < inventory.getContents().size(); i++) {
      if (inventory.getContents().get(i) != null) {
        WorldObject item = inventory.getContents().get(i);
        optionItems[i].setEnabled(true);

        int j = i; //int needs to be final for lambda expression
        optionItems[i].addActionListener(e -> {
          player.getInventory().add(item);
          player.toggleUpdateNeeded(); //needs to update craft gui
          inventory.getContents().remove(item);
          optionItems[j].setText("");
          optionItems[j].setEnabled(false);
        });
        optionItems[i].setText(item.getName());
        optionItems[i].setToolTipText(item.getDescription());
        optionItems[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      } else {
        i = inventory.getContents().size();
      }
    }

    for(int j=inventory.getContents().size(); j<optionItems.length; j++){
      optionItems[j].setToolTipText("Empty Slot");
      optionItems[j].setEnabled(false);
      optionItems[j].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  public void updateContainerGUI() {
    for (JButton optionItem : optionItems) {
      for(ActionListener l : optionItem.getActionListeners()){
        optionItem.removeActionListener(l);
      }
      optionItem.setToolTipText("Empty Slot");
      optionItem.setText("");
      optionItem.setEnabled(false);
      optionItem.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  public boolean isActive(){
    return this.isActive;
  }

  public void setActive(boolean value){
    this.isActive = value;
  }
}
