import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WindowHunting {
	public static ArrayList<Window> windows;
	public static ArrayList<Monster> monsters;
static int width=300;
static int height=300;
	public static void main(String[] args) {
		new WindowHunting();
	}
	public WindowHunting() {
		windows = new ArrayList<Window>();
		monsters=new ArrayList<Monster>();
		make(5);
		
		Protagonist.setLocation(windows.get(0));
		monsters.add(new Monster());
		monsters.get(0).setLocation(windows.get(4));
		windows.get(1).getGraphics().drawString("", 0, 0);
	}
	void make(int amount) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Random ran = new Random();
		for(int i = 0;i<amount;i++) {
			
			new Window(width,height).setLocation(ran.nextInt(d.width-width), ran.nextInt(d.height-height));
		}
	}

}
