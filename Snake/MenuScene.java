/*This class will define the main menu scene of the game
 * Daniel Spear
 * 5/9/2022
 */
package Snake;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class MenuScene extends Scene{
	
	public KL keyListener;
	public ML mouseListener;
	public BufferedImage title,play,playPressed,exit,exitPressed;
	public Rect playRect,exitRect,titleRect;
	
	public BufferedImage playCurrentImage, exitCurrentImage;
	
	//constructor
	public MenuScene(KL keyListener, ML mouseListener) {
		this.keyListener = keyListener;
		this.mouseListener = mouseListener;
		//creates all subimages from the graphic to put on screen
		try {
			BufferedImage spriteSheet = ImageIO.read(new File("Assets/menuSprite.png"));
			title = spriteSheet.getSubimage(0, 242, 960, 240);
			play = spriteSheet.getSubimage(0, 121, 261, 121);
			playPressed = spriteSheet.getSubimage(264, 121, 261, 121);
			exit = spriteSheet.getSubimage(0, 0, 233, 93);
			exitPressed = spriteSheet.getSubimage(264, 0, 233, 93);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		titleRect = new Rect(230, 100, 320, 100);
		playRect = new Rect(310, 280, 150, 70);
		exitRect = new Rect(318, 355, 130, 55);
		playCurrentImage = play;
		exitCurrentImage = exit;
	}
	
	/*updates the screen during run time
	 * @param double cit is the change in time since the last update
	 */
	public void update(double cit) {
		//checks if mouse on play to change color
		if(mouseListener.getX()>=playRect.x&&mouseListener.getX()<=playRect.x+playRect.width
		&&mouseListener.getY()>=playRect.y&&mouseListener.getY()<=playRect.y+playRect.height) {
			playCurrentImage = playPressed;
			//checks if play clicked to change screen
			if(mouseListener.isPressed()) {
				Window.getWindow().changeState(1);
			}
		}else {
			playCurrentImage = play;
		}
		//checks if mouse on exit to change color
		if(mouseListener.getX()>=exitRect.x&&mouseListener.getX()<=exitRect.x+exitRect.width
		&&mouseListener.getY()>=exitRect.y&&mouseListener.getY()<=exitRect.y+exitRect.height) {
			exitCurrentImage = exitPressed;
			//checks if exit pressed to close program
			if(mouseListener.isPressed()) {
				Window.getWindow().close();
			}
		}else {
			exitCurrentImage = exit;
		}
	}
	
	/*This method will draw the menu screen
	 * 
	 */
	public void draw(Graphics g) {
		g.setColor(new Color(120,175,182));
		g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		g.drawImage(title, (int)titleRect.x, (int)titleRect.y, (int)titleRect.width, (int)titleRect.height, null);
		g.drawImage(playCurrentImage, (int)playRect.x, (int)playRect.y, (int)playRect.width, (int)playRect.height, null);
		g.drawImage(exitCurrentImage, (int)exitRect.x, (int)exitRect.y, (int)exitRect.width, (int)exitRect.height, null);
	}
}
