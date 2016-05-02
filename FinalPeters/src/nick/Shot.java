package nick;

import java.awt.Color;
import java.awt.Graphics;

public class Shot {
	private double shotX = -1000;
	private double shotY = -1000;
	private double xVel = 0;
	private double yVel = 0;
	private int width = 4;
	private int height = 4;
	private boolean fired = false;
	
	public void shoot(int x_pos, int y_pos, int endX, int endY)
	{
		this.shotX = x_pos;
		this.shotY = y_pos;
		//set velocity 
		
		this.xVel = (endX-x_pos)/1000.0 * 20;
		this.yVel = (endY-y_pos)/1000.0 * 20;
		
		while((xVel < 7.5 && xVel > -7.5 && yVel < 7.5 && yVel > -7.5) || (xVel > 8.5 && xVel < -8.5 && yVel > 8.5 && yVel < -8.5))
		{
			if(xVel < 7.5 && xVel > -7.5 && yVel < 7.5 && yVel > -7.5)
			{
				xVel *= 2;
				yVel *= 2;
			}
			if(xVel > 8.5 && xVel < -8.5 && yVel > 8.5 && yVel < -8.5)
			{
				xVel /= 2;
				yVel /= 2;				
			}
		}
		fired = true;
		
		//System.out.println(xVel+"  "+yVel);

	}
	public void paintShot(Graphics g)
	{			
		updatePosition();
		g.setColor(Color.blue);
		g.fillOval( (int)(shotX) -2, (int)(shotY)-2, width, height);
	}
	
	public int getShotX()	{	return (int)(shotX);	}
	public int getShotY()	{	return (int)(shotY);	}
	public boolean getFired()	{	return fired;	}
	
	private void updatePosition()
	{
		shotX += xVel;
		shotY += yVel;
	}
	
	public void loaded()
	{
		fired = false;
	}
	
	
}
