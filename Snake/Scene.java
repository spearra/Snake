/*This class will serve as an abstract class to define all of the Scenes
 * Daniel Spear
 * 5/9/2022
 */
package Snake;

import java.awt.Graphics;

public abstract class Scene {
	public abstract void update(double cit);
	public abstract void draw(Graphics g);
}
