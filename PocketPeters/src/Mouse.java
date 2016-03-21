import java.awt.event.*;
public class Mouse implements MouseListener
{
	private Peters peter;
	public Mouse(Peters peter)
	{
		this.peter = peter;
	}
	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e)
	{
		peter.setCoord(e.getX(),e.getY());
	}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
}