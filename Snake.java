package Snake;

/*This class holds the snake object and controlls growing, intersection, and direction changes
 * Daniel Spear
 * 5/12/2022
 */

import java.awt.*;
import java.util.ArrayList;

public class Snake {
	public ArrayList<Rect> body = new ArrayList<Rect>();
	public double bodyWidth, bodyHeight;
	/*public int size;
	public int tail = 0;
	public int head = 0;
	*/
	
	public Direction direction = Direction.RIGHT;
	
	public double waitBetweenUpdates = .8;
	public double waitTimeLeft = waitBetweenUpdates;
	public Rect background;
	
	public int score=0;
	
	//constructor
	public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background) {
		//this.size = size;
		this.bodyWidth = bodyWidth;
		this.bodyHeight = bodyHeight;
		this.background = background;
		
		//initialize snake's body
		for(int i=0; i<size;i++) {
			//initializes to snake straight from left to right
			Rect bodyPiece = new Rect(startX+i*bodyWidth, startY, bodyWidth,bodyHeight, Direction.RIGHT);
			/*body[i] = bodyPiece;
			head++;
			*/
			body.add(bodyPiece);
		}
		//head--;
	}
	
	/*This method changes the direction of the snake's movement
	 * @param Direction newDirection is the newDirection for the snake to move
	 */
	public void changeDirection(Direction newDirection) {
		if(newDirection==Direction.RIGHT && direction!=Direction.LEFT)
			direction = newDirection;
		else if(newDirection==Direction.LEFT && direction!=Direction.RIGHT)
			direction = newDirection;
		else if(newDirection==Direction.UP && direction!=Direction.DOWN)
			direction = newDirection;
		else if(newDirection==Direction.DOWN && direction!=Direction.UP)
			direction = newDirection;
	}
	
	/*This method will update the snake as the game plays
	 * @param cit is the change in time since the last update
	 */
	public void update(double cit) {
		//controls time between movements
		if(waitTimeLeft>0) {
			waitTimeLeft= waitTimeLeft - (cit/100000000);
			return;
		}
			
		waitTimeLeft = waitBetweenUpdates;
		
		//moves snake one unit in direction
		double newX = 0;
		double newY = 0;
		if(direction==Direction.RIGHT) {
			newX = body.get(body.size()-1).x+bodyWidth;
			newY = body.get(body.size()-1).y;
		}
		else if(direction==Direction.LEFT) {
			newX = body.get(body.size()-1).x-bodyWidth;
			newY = body.get(body.size()-1).y;
		}
		else if (direction==Direction.UP) {
			newX = body.get(body.size()-1).x;
			newY = body.get(body.size()-1).y-bodyHeight;
		}
		else if (direction==Direction.DOWN){
			newX = body.get(body.size()-1).x;
			newY = body.get(body.size()-1).y+bodyHeight;
		}
		
		//moves last body piece to new position
		/*body[(head+1)%body.length] = body[tail];
		body[tail]=null;
		head = (head+1)%body.length;
		tail = (tail+1)%body.length;
		*/
		body.add(body.remove(0));
		
		body.get(body.size()-1).x = newX;
		body.get(body.size()-1).y = newY;
		body.get(body.size()-1).direction = direction;
		
		//checks if intersecting
		if(intersectingWithSelf()||intersectingWithScreenBoundaries(body.get(body.size()-1))) {
			Window.getWindow().changeState(0);
		}
		
	}
	
	/*Checks if the snake is intersecting with itsels
	 * @returns boolean of if intersecting with self
	 */
	public boolean intersectingWithSelf() {
		Rect headR = body.get(body.size()-1);
		for(int i=0; i<body.size()-1; i++) {
			if(intersecting(headR, body.get(i))) {
				System.out.println(i);
				return true;
			}
		}
		return false;
	}
	
	/*Checks if the snake is intersecting with the screen boundaries
	 * @returns boolean of if intersecting with boundaries
	 */
	public boolean intersectingWithScreenBoundaries(Rect head){
		return(head.x<background.x || head.x+head.width>background.x+background.width
			||head.y<background.y || head.y+head.height>background.y+background.height);
	}
	
	/*Checks if the snake is intersecting with any rectangle
	 * @returns boolean of if intersecting with the rectangle
	 */
	public boolean intersectingWithRect(Rect rect) {
		for(int i=0; i<body.size(); i++) {
			if(intersecting(rect, body.get(i))) {
				System.out.println(i);
				return true;
			}
		}
		return false;
	}
	
	/*Checks if the a rectangle is intersecting with another rectangle
	 * @returns boolean of if intersecting with other rectangle
	 */
	public boolean intersecting(Rect r1, Rect r2) { 
		return(r1.x>=r2.x && r1.x+r1.width<=r2.x+r2.width &&
			   r1.y>=r2.y && r1.y+r1.height<=r2.y+r2.height);
	}
	
	//grows the snake by adding a body peice in the opposite direction of tail's movement
	public void grow() {
		score++;
		
		double newX=0, newY=0;
		if(body.get(0).direction==Direction.RIGHT) {
			newX = body.get(0).x-bodyWidth;
			newY = body.get(0).y;
		}
		else if(body.get(0).direction==Direction.LEFT) {
			newX = body.get(0).x+bodyWidth;
			newY = body.get(0).y;
		}
		else if (body.get(0).direction==Direction.UP) {
			newX = body.get(0).x;
			newY = body.get(0).y+bodyHeight;
		}
		else if (body.get(0).direction==Direction.DOWN){
			newX = body.get(0).x;
			newY = body.get(0).y-bodyHeight;
		}
		
		/*tail = (tail-1) % body.length;
		if(tail==-1) {
			tail=99;
		}
		body[tail] = new Rect(newX, newY,bodyWidth,bodyHeight);*/
		body.add(0, new Rect(newX, newY, bodyWidth, bodyHeight));
	}
	
	/*draws the snake
	 * @param Graphics g is the graphics object that draws
	 */
	public void draw(Graphics g) {
		//draw scre
		Font f = new Font("Courier", Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString(score+"", 110, 70);
		//allows for snake to "wrap around"
		for(int i=0; i!=body.size(); i++) {
			g.setColor(new Color(255,0,185));
			g.fillRect((int)body.get(i).x,(int)body.get(i).y, (int)body.get(i).width-2, (int)body.get(i).height-2);
		}
	}
}
