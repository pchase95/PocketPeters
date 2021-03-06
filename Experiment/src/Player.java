import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener
{
	private int x_pos, y_pos;
	private int x_vel = 0, y_vel = 0;
	private int speed = 5;
	
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
	
	public void updatePosition()
	{
		x_pos+=x_vel;
		y_pos+=y_vel;
	}
	
	public void setXVEL(int i) { this.x_vel = i; }
	public void setYVEL(int i) { this.y_vel = i; }
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_W)
			y_vel = -speed;
		if(e.getKeyCode() == KeyEvent.VK_A)
			x_vel = -speed;
		if(e.getKeyCode() == KeyEvent.VK_S)
			y_vel = speed;
		if(e.getKeyCode() == KeyEvent.VK_D)
			x_vel = speed;
	}
	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_W)
			y_vel = 0;
		if(e.getKeyCode() == KeyEvent.VK_A)
			x_vel = 0;
		if(e.getKeyCode() == KeyEvent.VK_S)
			y_vel = 0;
		if(e.getKeyCode() == KeyEvent.VK_D)
			x_vel = 0;
	}
}
