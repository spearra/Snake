/*This class serves to hold the infor for rectangles to be drawn for the snake, background, and other graphics
 * Daniel Spear
 * 5/12/2022
 */
package Snake;

public class Rect {
	public double x,y,width,height;
	public Direction direction = null;
	
	//constructor without direction (for background/graphics)
	public Rect(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	//constructor with direction (for body parts)
	public Rect(double x, double y, double width, double height, Direction direction) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.direction=direction;
	}
}
