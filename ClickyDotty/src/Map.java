import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Map extends JPanel implements MouseListener, ActionListener
{
	private Peter peter;
	private List<Peter> peters = new ArrayList<>();
	private PositionListener PL;
	private JFrame frame;
	private boolean moving = false;
	
	public Map(){}
	/*
	public Map(Peter peter, PositionListener PL)
	{
		this.PL = PL;
		this.peter = peter;
		buildMap();
	}
	*/
	
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
		for(Peter p : peters)
			p.paintPeter(g);
	}
	
	public boolean getMoving()
	{
		return moving;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	
	public void mouseClicked(MouseEvent e)
	{
		PL.movePeter(e.getX(), e.getY(), peter);
		peter.setMove(true);		
	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	
	
	public void addPeter(Peter p) { peters.add(p); }
	public void setPeter(Peter p){ this.peter = p; }
	public void setPL(PositionListener PL) { this.PL = PL; }
}


