import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Map extends JPanel implements ActionListener
{
	//private Peters peter = new Peters();
	private Mouse mouse = new Mouse();
	public static void main(String[]args)
	{
		new Map().go();
	}

	private void go()
	{
		buildMap();
	}

	private void buildMap()
	{
		Frame frame = new Frame("PC:MO");
		frame.setSize(650,650);
		frame.add(this);
		frame.setVisible(true);
		this.setFocusable(true);
		Timer timer = new Timer(40,this);
		timer.start();
	}

	public Map()
	{
		addMouseListener(mouse);
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect(0,0,650,650);
		mouse.peter.paintPeter(g);

	}

	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	/*public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e)
	{
		peter.setCoord(e.getX(),e.getY());
		repaint();

	}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}*/
}