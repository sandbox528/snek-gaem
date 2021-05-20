package snek;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

public class StartMenu {
	public static int cellSize = 30;
	private int x,y = 0;
	
	public final Font F_LARGE = new Font("Serif", Font.PLAIN, 32);
	public final Font F_MED = new Font("Serif", Font.PLAIN, 20);
	public final Font F_SMALL = new Font("Serif", Font.PLAIN, 12);

	public StartMenu() {};
	
	public void draw(Graphics2D g) {

		
        g.setFont(F_LARGE);

		g.drawString("Welcom 2 snek gam!",10,30);

        g.setFont(F_MED);

		g.drawString("Pres s now ok",0,60);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
