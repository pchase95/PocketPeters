import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player extends JPanel implements KeyListener
{
	private int x_pos; 
	private int x_vel;
	private int y_pos;
	private int y_vel;
	
	public Player(int x_pos, int y_pos)
	{
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}
	
	public void paintPlayer(Graphics g)
	{
		updatePosition();
		g.setColor(Color.RED);
		g.fillOval(x_pos-15, y_pos-15, 30, 30);
		
	}

	private void updatePosition()
	{
		x_pos += x_vel;
		y_pos += y_vel;
	}

	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			x_vel = -20;
			System.out.print("Meme");
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			x_vel = 20;
		if(e.getKeyCode() == KeyEvent.VK_UP)
			y_vel = -20;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			y_vel = 20;
	}		
	public void keyReleased(KeyEvent e)
	{
		x_vel = 0;
		y_vel = 0;
	}
	public void keyTyped(KeyEvent e){}
}
