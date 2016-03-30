import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Map extends JPanel implements MouseListener, ActionListener
{
	private JFrame frame;
	private Client client;
	
	private Map(){}
	public Map(Client client)
	{
		this.client = client;
		buildMap();
	}
	
	public void buildMap()
	{
		frame = new JFrame("PC:MO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650,650);
		frame.add(this);
		Timer timer = new Timer(40,this);
		timer.start();
		this.setFocusable(true);
		frame.setVisible(true);
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0,0,650,650);
		for(Peter p : client.getPeters())
		{
			p.paintPeter(g);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
	
	public void mouseClicked(MouseEvent e)
	{
		client.movePeter(e.getX(), e.getY());
	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}

}


