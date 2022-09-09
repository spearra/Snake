/*This program will serve as the main class/driver for the snake program
 * Daniel Spear
 * 5/9/2022
 */
package Snake;

public class Main {
	public static void main(String[] args) {
		
		//create window
		Window w = Window.getWindow();
		
		//create thread
		Thread t = new Thread(w);
		
		//start thread and run program
		t.start();
	}
}
