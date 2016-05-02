import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class PopulationModel extends JComponent 
{
	Gene path;
	int iteration;
	int counter;
	boolean solved=false;
	public void update(Gene path,int iteration,int counter)
	{
		this.iteration=iteration;
		this.counter=counter;
		this.path=path;
		this.repaint();
	}
	public void solved(Gene path)
	{
		this.path=path;
		this.solved=true;
		this.repaint();
	}
	@Override
	protected void paintComponent(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		String iterCount = "Generation="+iteration+ "   Counter="+counter+ "   Distance="+path.distance();
		g2.drawString(String.valueOf(iterCount), 0, this.getHeight()-20);
		for(int i =0; i < path.size();++i)
		{
			
			g2.fillOval((int)path.get(i).getX()-5, (int)path.get(i).getY()-5, 10, 10);
			if(i<path.size()-1)
				g.drawLine((int)path.get(i).getX(), (int)path.get(i).getY(), (int)path.get(i+1).getX(), (int)path.get(i+1).getY());
			if(solved)
			{
				String tmp="";
				for(City c : path.iterable())
				{
					tmp+= c.getName()+" ";
				}
				g2.drawString(tmp, 0, this.getHeight());
				g2.drawString(path.get(i).getName(), (int)path.get(i).getX()-5, (int)path.get(i).getY()-5);
			}
		}
		
		
	}
}
