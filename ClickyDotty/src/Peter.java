import java.awt.Color;
import java.awt.Graphics;

public class Peter
{
	private int x;
	private int y;
	private int moveToX;
	private int moveToY;
	private boolean move = false;
	
	public Peter(){}
	public Peter(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void paintPeter(Graphics g)
	{
		if(move)
		{
			//moves peter and puts a dot where to move to
			movePeter(moveToX,moveToY);
			g.setColor(Color.green);
			g.fillOval(moveToX-2,moveToY-2,4,4);
		}
		g.setColor(Color.blue);
		g.fillOval(x-10,y-10,20,20);
	}
	
	public void movePeter(int moveToX, int moveToY)
	{
		this.moveToX = moveToX;
		this.moveToY = moveToY;
		//move x
		if(moveToX < x)
		{
			if(moveToX-x > -10)
			{
				x = moveToX;
			}
			else
			{
				x = x - 10;
			}
		}
		if(moveToX > x)
		{
			if(moveToX-x < 10)
			{
				x = moveToX;
			}
			else
			{
				x = x + 10;
			}
		}
		//move y
		if(moveToY < y)
		{
			if(moveToY-y > -10)
			{
				y = moveToY;
			}
			else
			{
				y = y - 10;
			}
		}
		if(moveToY > y)
		{
			if(moveToY-y < 10)
			{
				y = moveToY;
			}
			else
			{
				y = y + 10;
			}
		}

		//turns off move
		if(moveToY == y && moveToX == x)
		{
			move = false;
		}
	}
	
	public boolean getMove()
	{
		return move;
	}
	public void setMove(boolean b)
	{
		this.move = b;
	}
	
	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}
	
	public int getNewX()
	{
		return this.moveToX;
	}
	public int getNewY()
	{
		return this.moveToY;
	}
	public void setNewX(int a)
	{
		this.moveToX = a;
	}
	public void setNewY(int a)
	{
		this.moveToY = a;
	}
	

}