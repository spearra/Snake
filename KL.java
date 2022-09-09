/*This class is a KeyListener that will be used to read the user's key input
 * Daniel Spear
 * 5/9/2022
 */
package Snake;

import java.awt.event.*;

public class KL extends KeyAdapter implements KeyListener{
	private boolean[] keyPressed = new boolean[128];
	
	//records what key is pressed
	//@param keyEvent is the keyEvent that we are checking
	public void keyPressed(KeyEvent keyEvent) {
		keyPressed[keyEvent.getKeyCode()] = true;
	}
	
	//records when keys are released
	//@param keyEvent is the keyEvent that we are checking
	public void keyReleased(KeyEvent keyEvent) {
		keyPressed[keyEvent.getKeyCode()] = false;
	}
	
	//returns the value of keyPressed[] at index
	//@param int keyCode is the code for the key to check if it is pressed
	public boolean isKeyPressed(int keyCode) {
		return keyPressed[keyCode];
	}
}
