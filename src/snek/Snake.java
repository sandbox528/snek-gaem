package snek;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Snake {
	public enum Direction {UP, DOWN, LEFT, RIGHT}
	
	//size of cell
	public static int cellSize = 30;
	
	//# of frames to move across 1 cell
	private final int SPEED = 10;
	//Counts number of frames moved, resets at next cell
	private int AnimationStep = 0;
	//list of cells that compose the snek's body
	private ArrayList<int[]> body = new ArrayList<int[]>();
	//cell containing snek's head
	private int[] head = {0,0};
	
	//Direction currently moving
	Direction dir = Direction.RIGHT;
	//Holds the next direction until the snake can turn
	Direction nextDir = Direction.RIGHT;
	
	private boolean growing, dead = false;

	Snake() {
	}
	
	Snake(int[] head){
		this.head = head;
	}
	
	Snake(int[] head, ArrayList<int[]> body){
		this.body = body;
		this.head = head;
	}
	
	public void draw (Graphics2D g) {
		g.setStroke(new BasicStroke(cellSize*0.85f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		
		if (dead)
			g.setColor(Color.decode("#602000"));
		else
			g.setColor(Color.decode("#008000"));
		
		
		
		double offset = (((double)AnimationStep/(double)SPEED));
		double[] offsetHead = Arrays.stream(head).asDoubleStream().toArray();;
		switch (dir) {
		case RIGHT:
			offsetHead[0] += offset;
			break;
		case LEFT:
			offsetHead[0] -= offset;
			break;
		case DOWN:
			offsetHead[1] += offset;
			break;
		case UP:
			offsetHead[1] -= offset;
		}
		
		int[] xCoords = new int[body.size()+2];
		int[] yCoords = new int[body.size()+2];
		
		if (growing) {
			xCoords[0] = (body.get(0)[0]*cellSize)+cellSize/2;
			yCoords[0] = (body.get(0)[1]*cellSize)+cellSize/2;
		}
		else {
			xCoords[0] = (int)((body.get(0)[0]+((double)AnimationStep/SPEED)*(body.get(1)[0]-body.get(0)[0]))*cellSize)+cellSize/2;
			yCoords[0] = (int)((body.get(0)[1]+((double)AnimationStep/SPEED)*(body.get(1)[1]-body.get(0)[1]))*cellSize)+cellSize/2;
		}
		for (int i=1; i<body.size(); i++) {

				xCoords[i] = body.get(i)[0]*cellSize+cellSize/2;
				yCoords[i] = body.get(i)[1]*cellSize+cellSize/2;
		}
		xCoords[body.size()] = head[0]*cellSize+cellSize/2;
		yCoords[body.size()] = head[1]*cellSize+cellSize/2;
		xCoords[body.size()+1] = (int) (offsetHead[0]*cellSize)+cellSize/2;
		yCoords[body.size()+1] = (int) (offsetHead[1]*cellSize)+cellSize/2;
		
		g.drawPolyline(xCoords,yCoords,body.size()+2);
		g.fillOval((int) (offsetHead[0]*cellSize),(int) (offsetHead[1]*cellSize), cellSize, cellSize);

	}
	
	public void update () {
		AnimationStep++;

		if (AnimationStep>=SPEED) {
			AnimationStep = 0;
			body.add(head.clone());
			if (growing)
				growing = false;
			else
				body.remove(0);
			switch (dir) {
			case RIGHT:
				head[0]++;
				break;
			case LEFT:
				head[0]--;
				break;
			case DOWN:
				head[1]++;
				break;
			case UP:
				head[1]--;
			}
			dir = nextDir;
			
		}
	}


// Conditionally sets new direction 
	public void move(Direction dir) {

      switch (dir) {
        case LEFT:
        case RIGHT:

          if (this.dir == Direction.UP || this.dir == Direction.DOWN) {
            nextDir = dir;
          }
          break;

        case UP:
        case DOWN:

          if (this.dir == Direction.LEFT || this.dir == Direction.RIGHT) {
            nextDir = dir;
          }

          break;

        default:
          break;
      }
	}
	
	public boolean occupiesCell(int x, int y){
		int[] cell = new int[2];
		cell[0] = x; cell[1]=y;
		
		if (Arrays.equals(head,cell))
			return true;
		for (int[] location: body)
			if (Arrays.equals(location, cell))
				return true;
			return false;
	}
	
	public boolean checkCollision(int boardWidth, int boardHeight) {
		if (head[0]<=0 && dir==Direction.LEFT)
			dead = true;
		if (head[0]>=boardWidth-1 && dir==Direction.RIGHT)
			dead = true;
		if (head[1]<=0 && dir==Direction.UP)
			dead = true;
		if (head[1]>=boardHeight-1 && dir==Direction.DOWN)
			dead = true;
		
		if (dead)
			return true;
		
		for (int[] cell:body) {
			if (Arrays.equals(cell, head)) {
				dead = true;
				break;
			}
		}
		return dead;
		}
	
	public void grow() {growing = true;}
}

