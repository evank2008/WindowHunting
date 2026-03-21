import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Monster {
	private Window location;
	//TODO figure out pathfinding. current issue is that if monster is, for example, down and right of protag
	//and it decides to try moving up
	//but the closest window up if to the right
	//trying to move up will move you right
	//which is away from protag
	//maybe try a different approach where you have a method that explicitly
	//tries to find the closest window toward protagonist
	//without using getClosest methods
	
	
	public Window getLocation() {
		return location;
	}
	public  void setLocation(Window w) {
		if(location!=null) {
			location.panel.monsterOn=false; 
			location.repaint();
			}
		location=w;
		location.panel.monsterOn=true;
		location.repaint();
	}
	public Window getPathToWindow(Window win) {
		Point loc = location.getLocation();
		Point protag = win.getLocation();
		
		int xDiff = protag.x-loc.x; //positive number if monster is left/above protag
		int yDiff = protag.y-loc.y;
		
		if(Math.abs(xDiff)<Math.abs(yDiff)) {
			System.out.println("moving horz attempt");
			//try to move horizontally
			Window w = xDiff>0?location.closestRight():location.closestLeft();
			if(w!=location&&isCloserToThan(w,win,location)) {
				return w;
			} //if not then cant move horizontally, switch to vertical
		} else {
			//try to move vertically
			System.out.println("moving vert attempt");
			Window w = yDiff>0?location.closestDown():location.closestUp();
			if(w!=location&&isCloserToThan(w,win,location)) {
				return w;
			}
		}
		//cant move horizontally nor vertically
		return null;
	}
	boolean isCloserToThan(Window newWin, Window destination, Window oldWin) {
		//returns true if newWin is closer to destination than oldWin
		if(newWin.getLocation().distance(destination.getLocation())<oldWin.getLocation().distance(destination.getLocation())) {
			return true;
		}
		return false;
	}
	void moveWindowAtProtag() {
		System.out.println("rahhhh");
	}
	static void drawMonster(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(WindowHunting.width*7/21, WindowHunting.height*7/21, WindowHunting.width/3, WindowHunting.height/3);
	}
}
