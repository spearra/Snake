/*This class will define the game scene for playing snake and handle changing directions
 * Daniel Spear
 * 5/9/2022
 */
package Snake;

import java.awt.*;
import java.awt.event.KeyEvent;


public class GameScene extends Scene{
	Rect background,foreground;
	Snake snake;
	KL keyListener;
	public Food food;
	
	//constructer
	public GameScene(KL keyListener) {
		//31 columns and 22 rows, all width and height of 24
		background = new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
		foreground = new Rect(24,48,Constants.TILE_WIDTH*31,Constants.TILE_WIDTH*22);
		snake= new Snake(5,48,72,24,24,foreground);
		this.keyListener = keyListener;
		food = new Food(foreground, snake, 12, 12, new Color(49,255,49));
		food.spawn();
	}
	
	/*This method will update the screen during runtime
	 * @param double cit is the change in time since the last update
	 */
	public void update(double cit) {
		//sees user key input and changes direction
		if(keyListener.isKeyPressed(KeyEvent.VK_UP)) {
			snake.changeDirection(Direction.UP);
		}
		else if(keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
			snake.changeDirection(Direction.DOWN);
		}
		else if(keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
			snake.changeDirection(Direction.RIGHT);
		}
		else if(keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
			snake.changeDirection(Direction.LEFT);
		}
		
		//spawns food if needed
		if(!food.isSpawned) {
			food.spawn();
		}
		//updates aspects of the screen
		food.update(cit);
		snake.update(cit);
	}
	
	/*This method draws the background and foreground of the game scene
	 * @param Graphics g is the graphics object that draws
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)background.x,(int)background.y,(int)background.width,(int)background.height);
		g.setColor(Color.BLACK);
		g.fillRect((int)foreground.x,(int)foreground.y,(int)foreground.width,(int)foreground.height);
		Font f = new Font("Courier", Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("Score: ", 30, 70);
		//draws other aspects of the screen
		snake.draw(g);
		food.draw(g);
	}
}
