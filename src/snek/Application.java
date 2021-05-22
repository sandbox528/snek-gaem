package snek;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Application extends JFrame {

    public Application() {

        initUI();
    }

    private void initUI() {

        Board gameBoard = new Board();

        JPanel startMenu = new JPanel();
        startMenu.setPreferredSize(new Dimension(360, 360));
        JButton b = new JButton("Start");

        startMenu.add(b);
        add(startMenu);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add(gameBoard);
                startMenu.setVisible(false);
                gameBoard.Run();
                gameBoard.grabFocus();
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
