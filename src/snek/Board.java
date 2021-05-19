package snek;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import snek.Snake.Direction;

public class Board extends JPanel
    {
	private Timer tm;
	
	//Game Board dimensions:
    private final int B_WIDTH = 350, B_HEIGHT = 350;
    
    //Size of cell in pixels
    private final int CELL_SIZE = 30;
    
    private final int DELAY = 25;
    
    private KeyAction ka = new KeyAction();
    
    Snake snek;
    
    class KeyAction implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyChar()) {
			case 'w':
				snek.move(Direction.UP);
				break;
			case 'a':
				snek.move(Direction.LEFT);
				break;
			case 's':
				snek.move(Direction.DOWN);
				break;
			case 'd':
				snek.move(Direction.RIGHT);
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}
		
    }
    
    public Board() {
        initBoard();
    }


    private void initBoard() {
    	setFocusable(true);

    	this.addKeyListener(ka);
    	tm = new Timer(15, new TimerListener());
    	tm.start();
 
    	 
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        Snake.cellSize = 30;
        ArrayList<int[]> body = new ArrayList<int[]>();
        int[] cell = new int[2];
        
        cell = new int[]{0,0};
        body.add(cell.clone());
        cell = new int[]{1,0};
        body.add(cell);
        cell = new int[]{2,0};
        snek = new Snake(cell,body);
        

    }


    //This draws the graphics for each frame:
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        snek.draw(g2);

    }


    //This runs each frame
    private void cycle() {
    	snek.update();
    }


    private class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			cycle();
			repaint();
			
		}
    }

    
}