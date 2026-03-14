import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	private boolean isReal=true;
	private static int perpendicularMaxRect=300;
	Panel panel;
static int number=0;
	public Window(int width, int height) {
		super(""+number);
		number++;
		//setSize(width,height);
		setResizable(false);
		panel = new Panel();
		panel.setPreferredSize(new Dimension(width,height));
		add(panel);
		pack();
		
		setVisible(true);
		addKeyListener(new Protagonist());
		WindowHunting.windows.add(this);
	}
	private Window() {
		isReal=false;
		setTitle("aaaaaaaa");
	}
	public Window closestLeft() {
		Window closest=this;
		int dist=Integer.MAX_VALUE;
		for(Window w: WindowHunting.windows) {
			if(w==this) continue;
			int i;
			if(w.isReal&&(i=getLocation().x-w.getLocation().x)<dist&&i>0&&Math.abs(w.getLocation().y-getLocation().y)<=perpendicularMaxRect) {
				dist=i;
				closest=w;
			}
		}
		return closest;
	}
	public Window closestRight() {
		Window closest=this;
		int dist=Integer.MAX_VALUE;
		for(Window w: WindowHunting.windows) {
			if(w==this) continue;
			int i;
			if((i=w.getLocation().x-getLocation().x)<dist&&i>0&&Math.abs(w.getLocation().y-getLocation().y)<=perpendicularMaxRect) {
				dist=i;
				closest=w;
			}
		}
		return closest;
	}
	public Window closestUp() {
		Window closest=this;
		int dist=Integer.MAX_VALUE;
		for(Window w: WindowHunting.windows) {
			if(w==this) continue;
			int i;
			if((i=getLocation().y-w.getLocation().y)<dist&&i>0&&Math.abs(w.getLocation().x-getLocation().x)<=perpendicularMaxRect) {
				dist=i;
				closest=w;
			}
		}
		return closest;
	}
	public Window closestDown() {
		//found x value would be bigger than this's by smallest amount
		Window closest=this;
		int dist=Integer.MAX_VALUE;
		for(Window w: WindowHunting.windows) {
			if(w==this) continue;
			int i;
			if((i=w.getLocation().y-getLocation().y)<dist&&i>0&&Math.abs(w.getLocation().x-getLocation().x)<=perpendicularMaxRect) {
				dist=i;
				closest=w;
			}
		}
		return closest;
	}
	public boolean isReal() {
		return isReal;
	}

}
class Panel extends JPanel {
	public boolean protagonistOn=false;
	public boolean monsterOn = false;
	public Panel() {
		super();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!protagonistOn&&!monsterOn) {
			return;
		}
		if(protagonistOn&&monsterOn) {
			//idk
		}
		if(protagonistOn) {
			//draw protag
			//uk axis and allies flag token?
			g.setColor(Color.yellow);
			g.fillOval(WindowHunting.width*7/21, WindowHunting.height*7/21, WindowHunting.width/3, WindowHunting.height/3);
			g.setColor(Color.blue);
			g.fillOval(WindowHunting.width*8/21, WindowHunting.height*8/21, WindowHunting.width/3*5/7, WindowHunting.height/3*5/7);
			g.setColor(Color.white);
			g.fillOval(WindowHunting.width*9/21, WindowHunting.height*9/21, WindowHunting.width/3*3/7, WindowHunting.height/3*3/7);
			g.setColor(Color.red);
			g.fillOval(WindowHunting.width*10/21, WindowHunting.height*10/21, WindowHunting.width/3*1/7, WindowHunting.height/3*1/7);
		} else {
			//draw monster
		}
	}
}
