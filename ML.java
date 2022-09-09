/*This class serves as a mouse listener to record the user's mouse movement and clicks
 * Daniel Spear
 * 5/12/2022
 */
package Snake;

import java.awt.event.*;

public class ML extends MouseAdapter implements MouseMotionListener{
	public boolean isPressed = false;
	public double x=0, y=0;
	
	//records if mouse pressed
	//@param e is the mouse event that we are checking
	public void mousePressed(MouseEvent e) {
		isPressed = true;
	}
	
	//records if mouse released
	//@param e is the mouse event that we are checking
	public void mouseReleased(MouseEvent e) {
		isPressed = false;
	}
	
	//records where mouse moved to
	//@param e is the mouse event that we are checking
	public void mouseMoved(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}
	
	//accessors
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public boolean isPressed() {
		return isPressed;
	}
}
