/*This program will create the window that the game is to be played in
 * Daniel Spear
 * 5/9/2022
 */
package Snake;
import java.awt.*;
import javax.swing.JFrame;

public class Window extends JFrame implements Runnable{
	public boolean isRunning;
	public static Window window = null;
	
	public int currentState;
	public Scene currentScene;
	public KL keyListener = new KL();
	public ML mouseListener = new ML();
	
	/*returns the window, allows for all other methods to be non-static, initializes window if needed
	 * @return Window of the current window
	 */
	public static Window getWindow() {
		if(Window.window == null) {
			Window.window = new Window(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT,Constants.TITLE);
		}
		return Window.window;
	}
	
	//closes the program, sets to not running
	public void close() {
		isRunning=false;
	}
	
	/*Changes the state of the window to change the screen
	 * @param int newState is an integer representing what screen to change to -- 0 for menu 1 for game
	 */
	public void changeState(int newState) {
		currentState = newState;
		switch(currentState) {
			case 0:
				currentScene = new MenuScene(keyListener,mouseListener);
				break;
			case 1:
				currentScene = new GameScene(keyListener);
				break;
			default:
				System.out.println("Unknown scene");
				currentScene = null;
				break;
		}
	}
	
	//constructor
	public Window(int width, int height, String title) {
		isRunning = true;
		setTitle(title);
		setSize(width,height);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(keyListener);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		
		//initializes to start on menu
		changeState(0);
	}
	
	/*updates the window as the game plays
	 * @param cit is the change in time since the last update
	 */
	public void update(double cit) {
		Image dbImage = createImage(getWidth(), getHeight());
		Graphics dbg = dbImage.getGraphics();
		this.draw(dbg);
		getGraphics().drawImage(dbImage,0,0, this);
		
		currentScene.update(cit);
	}
	
	/*Draws the current scene that is up
	 * @param Graphics g is the graphics class to draw
	 */
	public void draw (Graphics g) {		
		currentScene.draw(g);
	}
	
	//actually runs the program/window, calling an update to everything else. after stops running, disposes the window
	public void run() {
		double lastFrameTime = 0;
		try {
			while(isRunning) {
				double time = Time.getTime();
				double changeInTime = time - lastFrameTime;
				lastFrameTime = time;
				
				update(changeInTime);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		this.dispose();
	}
}
