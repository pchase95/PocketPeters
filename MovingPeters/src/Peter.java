import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Peter implements Serializable
{
	private String ID;
	private int x;
	private int y;
	
	private Peter(){}
	public Peter(Peter p)
	{
		this.x = p.x;
		this.y = p.y;
		this.ID = p.ID;
	}
	public Peter(int x, int y, String ID)
	{
		this.x = x;
		this.y = y;
		this.ID = ID;
	}
	
	
	public void paintPeter(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillOval(x-10,y-10,20,20);
	}
	
	public void moveMe(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public void setX( int a ) { this.x = a; }
	public void setY( int a ) { this.y = a; }
	public String toString() { return String.format("%s%n",this.ID); }
	public String getID() { return this.ID; }
	
}