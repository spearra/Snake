package Snake;
/*This class will handel the spawning of food at random points
 * Daniel Spear
 * 5/12/2022*/

import java.awt.Color;
import java.awt.Graphics;

public class Food {
	public Rect background;
	public Snake snake;
	public int width,height;
	public Color color;
	public int xPadding;

	public Rect rect;
	
	public boolean isSpawned = false;
	
	//constructor
	public Food(Rect background, Snake snake, int width, int height, Color color) {
		this.background=background;
		this.snake=snake;
		this.width=width;
		this.height=height;
		this.color=color;
		this.rect = new Rect(0,0,width,height);
		xPadding = (int)((Constants.TILE_WIDTH - this.width)/2);
	}
	
	//this will spawn a food peice at a random spot in the grd that is not currently occupied by the snake
	public void spawn() {
		do {
			rect.x = (int)(Math.random()*(int)(background.width/Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.x;
			rect.y = (int)(Math.random()*(int)(background.height/Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.y;
		}
		while(snake.intersectingWithRect(rect));
		
		isSpawned = true;
	}
	
	/*this will update the food class as the program runs, growing the snake if it hits the food
	 * @param double cit is the change in time since the last update*/
	public void update(double cit) {
		if(snake.intersectingWithRect(rect)) {
			snake.grow();
			rect.x = -100;
			rect.y = -100;
			isSpawned = false;
		}
	}
	
	/*this will draw the food on screen
	 * @param Graphics g is the graphics object used to draw*/
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int)rect.x+xPadding,(int)rect.y+xPadding,(int)width,(int)height);
	}
}
