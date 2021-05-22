package snek;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Application extends JFrame {

    JPanel activeScreen;
    StartMenu startMenu;
    Board gameBoard;

    public Application() {

        // Having it set up this way will make it easier to add
        // new JPanels to switch to in the future (like one for settings maybe)

        this.startMenu = new StartMenu();
        this.gameBoard = new Board();
        this.activeScreen = this.startMenu;
        initUI();
    }

    private void setActiveScreen(JPanel screen) {

        // There is probably a better way to do this but it
        // works for now
        add(screen);
        activeScreen.setVisible(false);
        screen.grabFocus();
        activeScreen = screen;
    }

    private void initUI() {

        add(startMenu);

        // Button on the start menu triggers actual game to start
        startMenu.triggerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setActiveScreen(gameBoard);
                gameBoard.Run();
            }
        });

        setResizable(false);
        pack();
        setTitle("Snek");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new Application();
            ex.setVisible(true);
        });
    }
}
