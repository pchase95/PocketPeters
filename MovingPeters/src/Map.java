import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Map extends JPanel implements KeyListener, ActionListener
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
		addKeyListener(this);
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
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
			client.movePeter(client.getPeter().getX(), client.getPeter().getY()-10);
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			client.movePeter(client.getPeter().getX(), client.getPeter().getY()+10);
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			client.movePeter(client.getPeter().getX()-10, client.getPeter().getY());
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			client.movePeter(client.getPeter().getX()+10, client.getPeter().getY());
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}

}


