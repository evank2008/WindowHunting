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
	location=w;
	location.panel.protagonistOn=true;
	location.repaint();
}

}
