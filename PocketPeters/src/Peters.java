import java.awt.*;
import java.awt.event.MouseEvent;
public class Peters
{
	private int x;
	private int y;
	private int moveToX;
	private int moveToY;
	private boolean move = false;
	public Peters(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void paintPeter(Graphics g)
	{
		if(move)
		{
			//moves peter and puts a dot where to move to
			move();
			g.setColor(Color.green);
			g.fillOval(moveToX-2,moveToY-2,4,4);
		}
		g.setColor(Color.blue);
		g.fillOval(x-10,y-10,20,20);
	}

	public void setCoord(int x, int y)
	{
		this.moveToX = x;
		this.moveToY = y;
		move = true;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	private void move()
	{
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
	

}