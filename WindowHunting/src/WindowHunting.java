import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WindowHunting {
	public static ArrayList<Window> windows;
	public static ArrayList<Monster> monsters;
static int width=300;
static int height=300;
public static boolean explodingEnabled=false;
	public static void main(String[] args) {
		new WindowHunting();
	}
	public WindowHunting() {
		loadFont();
		windows = new ArrayList<Window>();
		monsters=new ArrayList<Monster>();
		make(5);
		
		Protagonist.setLocation(windows.get(0));
		monsters.add(new Monster());
		monsters.get(0).setLocation(windows.get(4));
		System.out.println("all code done? maybe");
		//TODO: figure out when to turn explodingEnabled true so that it doesnt kill you on startup but does kill you if you try moving any windows with moving disabled
		//explodingEnabled=true;
	}
	void make(int amount) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Random ran = new Random();
		for(int i = 0;i<amount;i++) {
			
			new Window(width,height).setLocation(ran.nextInt(d.width-width), ran.nextInt(d.height-height));
		}
	}
	void loadFont() {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setVisible(true);
		Graphics g = frame.getGraphics();
		g.drawString("sssdfg", 0, 0);
		frame.dispose();
	}

}
