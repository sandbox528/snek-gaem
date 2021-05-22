package snek;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Apple {
	public static int cellSize = 30;
	private int x, y = 0;

	public Apple() {
	};

	public Apple(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Apple(int boardWidth, int boardHeight, Snake snake) {
		Random rnd = new Random();
		do {
			x = rnd.nextInt(boardWidth);
			y = rnd.nextInt(boardHeight);
		} while (snake.occupiesCell(x, y));
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillOval((int) (x * cellSize), (int) (y * cellSize), cellSize, cellSize);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
