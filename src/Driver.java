import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Driver {
	//Taken from Kevin Loney https://stackoverflow.com/users/13834/kevin-loney
	public static void saveComponent(JComponent c, String format, String filename){
		try
		{
			BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = image.createGraphics();
			c.paint(graphics2D);
			ImageIO.write(image,format, new File(filename));
		}
		catch(IOException exception)
		{
		//code
		}
	}
	public static void main(String[] args) throws IOException {
		
		//Constants
		final int POP_SIZE = 1000;
		final double MUT_PROB = .1;
		final int TOURNEY_SIZE = 100;
		final int GENERATIONS  =300;
		ArrayList<City> cities = new ArrayList<City>();
		for(int i = 0; i< 15; ++i)
		{
			cities.add(new City(i*5647563456L%4675643567L+1, 600, 900, "R"+i));
		}
				
		
		Population pop = new Population(POP_SIZE, MUT_PROB, TOURNEY_SIZE,cities);
		PopulationModel model= new PopulationModel(pop.population.get(0));
		JFrame frame = new JFrame();
		frame.setSize(1050, 700);
		model.setSize(1000,700);
		frame.add(model);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		int counter=0;
		double best=Double.MAX_VALUE;
		for(int i = 0; i < 1000; ++i)
		{
			pop.evolve();
			System.out.println("Gen "+ i+ "\tHighest: " +pop.bestFitness()+ "\tAverage: " + pop.average()+"\tCounter: " + counter);
			if(pop.best.distance()<best)
			{
				best=pop.best.distance();
				counter=0;
			}
			else
			{
				++counter;
				if(counter==GENERATIONS)
				{
					model.solved(pop.best);
					System.out.println("Optimal path is: "+pop.best.toString());
					break;
				}
			}
			model.update(pop.best,i,counter);
			saveComponent(model, "png", "Gen"+i);
		}
	}
}
