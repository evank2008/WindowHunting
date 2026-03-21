import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.java.swing.plaf.windows.resources.windows;

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
	public Window closestLeft(int...ignored) {
		Window closest=this;
		int dist=Integer.MAX_VALUE;
		for(int j = 0;j<WindowHunting.windows.size();j++) {
			boolean containedInIgnored=false;
			for(int in: ignored) {
				if(in==j) {containedInIgnored=true;break;}
			} if(containedInIgnored) continue;
			Window w = WindowHunting.windows.get(j);
			if(w==this) continue;
			int i;
			if(w.isReal&&(i=(getLocation().x-w.getLocation().x))<dist&&i>0&&Math.abs(w.getLocation().y-getLocation().y)<=perpendicularMaxRect) {
				dist=i;
				closest=w;
			}
		}
		return closest;
	}
	public Window closestRight(int...ignored) {
		Window closest=this;
		int dist=Integer.MAX_VALUE;
		for(int j = 0;j<WindowHunting.windows.size();j++) {
			boolean containedInIgnored=false;
			for(int in: ignored) {
				if(in==j) {containedInIgnored=true;break;}
			} if(containedInIgnored) continue;
			Window w = WindowHunting.windows.get(j);
			if(w==this) continue;
			int i;
			if((i=(w.getLocation().x-getLocation().x))<dist&&i>0&&Math.abs(w.getLocation().y-getLocation().y)<=perpendicularMaxRect) {
				dist=i;
				closest=w;
			}
		}
		return closest;
	}
	public Window closestUp(int...ignored) {
		Window closest=this;
		int dist=Integer.MAX_VALUE;
		for(int j = 0;j<WindowHunting.windows.size();j++) {
			boolean containedInIgnored=false;
			for(int in: ignored) {
				if(in==j) {containedInIgnored=true;break;}
			} if(containedInIgnored) continue;
			Window w = WindowHunting.windows.get(j);
			if(w==this) continue;
			int i;
			if((i=(getLocation().y-w.getLocation().y))<dist&&i>0&&Math.abs(w.getLocation().x-getLocation().x)<=perpendicularMaxRect) {
				dist=i;
				closest=w;
			}
		}
		return closest;
	}
	public Window closestDown(int...ignored) {
		//found x value would be bigger than this's by smallest amount
		Window closest=this;
		int dist=Integer.MAX_VALUE;
		for(int j = 0;j<WindowHunting.windows.size();j++) {
			boolean containedInIgnored=false;
			for(int in: ignored) {
				if(in==j) {containedInIgnored=true;break;}
			} if(containedInIgnored) continue;
			Window w = WindowHunting.windows.get(j);
			if(w==this) continue;
			int i;
			if((i=(w.getLocation().y-getLocation().y))<dist&&i>0&&Math.abs(w.getLocation().x-getLocation().x)<=perpendicularMaxRect) {
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
			g.drawString("you died by the monster. ahhhh(dats you yelling)", 0, g.getClipBounds().height/2);
		}
		else if(protagonistOn) {
			//draw protag
			Protagonist.drawProtag(g);
		} else {
			//draw monster
			Monster.drawMonster(g);
		}
	}
}
