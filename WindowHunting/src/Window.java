import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Area;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	private boolean isReal=true;
	public static int perpendicularMaxRect=300;
	private boolean protagonistOn=false;
	private boolean monsterOn = false;
	Panel panel;
	private boolean moveable=true;
static int number=0;
	public Window(int width, int height) {
		super(""+number);
		number++;
		//setSize(width,height);
		setResizable(false);
		panel = new Panel(this, width, height);
		
		add(panel);
		pack();
		
		
		setVisible(true);
		addKeyListener(new Protagonist());
		addComponentListener(new ComponentAdapter() {
            public void componentMoved(ComponentEvent e) {
            	System.out.println("mpove attempt "+getTitle()+" "+e.paramString());
                if(WindowHunting.explodingEnabled&&!isMoveable()) {
                	System.out.println("you have died");
                	System.exit(0);
                }
            }
        });
		WindowHunting.windows.add(this);
		
	}
	private Window() {
		isReal=false;
		setTitle("aaaaaaaa");
	}
	public boolean getProtagOn() {
		return protagonistOn;
	}
	public void setProtagOn(boolean b) {
		protagonistOn=b;
		setMoveable(!b);
	}
	public boolean getMonsterOn() {
		return monsterOn;
	}
	public void setMonsterOn(boolean b) {
		monsterOn=b;
		setMoveable(!b);
	}
	public void setMoveable(boolean b) {
		moveable=b;
	}
	public boolean isMoveable() {
		return moveable;
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
	
	private Window parent;
	private final  Area border;
	int thickness = 10;
	public Panel(Window w, int width, int height) {
		super();
		parent = w;
		setPreferredSize(new Dimension(width,height));
		
		border = new Area(new Rectangle(0,0,getPreferredSize().width,getPreferredSize().height));
		Shape hole = new Rectangle(thickness,thickness,getPreferredSize().width-2*thickness,getPreferredSize().height-2*thickness);
		border.subtract(new Area(hole));
	}
	@Override
	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D)gg;
		if(!parent.isMoveable()) {
			g.setColor(Color.red);
			g.fill(border);
		}
		
		if(!parent.getProtagOn()&&!parent.getMonsterOn()) {
			return;
		}
		if(parent.getProtagOn()&&parent.getMonsterOn()) {
			g.drawString("you died by the monster. ahhhh(dats you yelling)", 0, g.getClipBounds().height/2);
		}
		else if(parent.getProtagOn()) {
			//draw protag
			Protagonist.drawProtag(g);
		} else {
			//draw monster
			Monster.drawMonster(g);
		}
	}
}
