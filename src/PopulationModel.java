import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class PopulationModel extends JComponent 
{
	ArrayList<City> path;
	boolean solved=false;
	public void update(ArrayList<City> path)
	{
		this.path=path;
		this.repaint();
	}
	public void solved(ArrayList<City> path)
	{
		this.path=path;
		this.solved=true;
		this.repaint();
	}
	@Override
	protected void paintComponent(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		
		for(int i =0; i < path.size();++i)
		{
			
			g2.fillOval((int)path.get(i).getX()-5, (int)path.get(i).getY()-5, 10, 10);
			if(i<path.size()-1)
				g.drawLine((int)path.get(i).getX(), (int)path.get(i).getY(), (int)path.get(i+1).getX(), (int)path.get(i+1).getY());
		}
		
	}
}
