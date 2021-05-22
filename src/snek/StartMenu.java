package snek;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;

// JPanel that is shown first thing when the app launches
class StartMenu extends JPanel {

  JButton triggerButton;

  StartMenu() {

    this.setPreferredSize(new Dimension(360, 360));
    this.triggerButton = new JButton("Start");
    this.add(triggerButton);

  }
}
