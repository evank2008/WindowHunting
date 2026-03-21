import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Protagonist implements KeyListener{
private static Window location;
static boolean aPr=false,sPr=false,dPr=false,wPr=false;
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	switch(e.getKeyCode()) {
	case KeyEvent.VK_A:
		if(!aPr) {
		setLocation(location.closestLeft());
		aPr=true;
		}
		break;
	case KeyEvent.VK_D:
		if(!dPr) {
		setLocation(location.closestRight());
		dPr=true;
		}
		break;
	case KeyEvent.VK_S:
		if(!sPr) {
		setLocation(location.closestDown());
		sPr=true;
		}
		break;
	case KeyEvent.VK_W:
		if(!wPr) {
		setLocation(location.closestUp());
		wPr=true;
		}
		break;
	case KeyEvent.VK_M: {
		for(Monster m: WindowHunting.monsters) {
			Window nextWindow = m.getPathToWindow(Protagonist.getLocation());
			if(nextWindow!=null) {
			m.setLocation(nextWindow);
			} else {
				m.moveWindowAtProtag();
			}
		}
	}
	}
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	switch(e.getKeyCode()) {
	case KeyEvent.VK_A:
		aPr=false;
		break;
	case KeyEvent.VK_D:
		dPr=false;
		break;
	case KeyEvent.VK_S:
		sPr=false;
		break;
	case KeyEvent.VK_W:
		wPr=false;
		break;
	}
}
public static Window getLocation() {
	return location;
}
public static void setLocation(Window w) {
	if(location!=null) {
		location.panel.protagonistOn=false; 
		location.repaint();
		}
	if(location!=w) {/*
		for(Monster m: WindowHunting.monsters) {
			Window nextWindow = m.getPathToWindow(w);
			if(nextWindow!=null) {
			m.setLocation(nextWindow);
			} else {
				m.moveWindowAtProtag();
			}
		}*/
	}
	location=w;
	location.panel.protagonistOn=true;
	location.repaint();
}
static void drawProtag(Graphics g) {
	g.setColor(Color.yellow);
	g.fillOval(WindowHunting.width*7/21, WindowHunting.height*7/21, WindowHunting.width/3, WindowHunting.height/3);
	g.setColor(Color.blue);
	g.fillOval(WindowHunting.width*8/21, WindowHunting.height*8/21, WindowHunting.width/3*5/7, WindowHunting.height/3*5/7);
	g.setColor(Color.white);
	g.fillOval(WindowHunting.width*9/21, WindowHunting.height*9/21, WindowHunting.width/3*3/7, WindowHunting.height/3*3/7);
	g.setColor(Color.red);
	g.fillOval(WindowHunting.width*10/21, WindowHunting.height*10/21, WindowHunting.width/3*1/7, WindowHunting.height/3*1/7);
}

}
