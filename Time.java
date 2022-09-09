package Snake;

/*This class will serve as a place to hold the time information of the program, including time started and time so far
 * Daniel Spear
 * 5/12/2022
 */

public class Time {
	public static double timeStarted = System.nanoTime();
	
	public static double getTime() {
		return (System.nanoTime() - timeStarted * Math.pow(10, -9));
	}
}
