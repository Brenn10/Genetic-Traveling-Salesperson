import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Driver {
	public static void main(String[] args) throws IOException {
		
		ArrayList<City> cities = new ArrayList<City>();
		cities.add(new City(540, 691, "First"));
		cities.add(new City(107, 304, "Second"));
		cities.add(new City(154, 447, "Third"));
		cities.add(new City(371, 407, "Fourth"));
		cities.add(new City(913, 668, "Fifth"));
		cities.add(new City(693, 662, "Sixth"));
		cities.add(new City(661, 170, "Seventh"));
		cities.add(new City(320, 97, "Eight"));
		cities.add(new City(791, 170, "Ninth"));
		
		
		Population pop = new Population(100, 0.3, 4,cities);
		
		JFrame frame = new JFrame();
		frame.setSize(1000, 700);
		PopulationModel model = new PopulationModel();
		model.setSize(1000,700);
		frame.add(model);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//Get initial values
		System.out.println("Highest: " +pop.bestFitness()+ "\tAverage: " + pop.average());
		
		int counter=0;
		double best=Double.MAX_VALUE;
		//Go through n generations
		for(int i = 0; i < 1000; ++i)
		{
			//evolve
			pop.evolve();
			
			model.update(pop.best.getArrayList());
			//output to console
			System.out.println("Gen "+ i+ "\tHighest: " +pop.bestFitness()+ "\tAverage: " + pop.average()+"\tCounter: " + counter);
			if(pop.best.distance()<best)
			{
				best=pop.best.distance();
				counter=0;
			}
			else
			{
				++counter;
				if(counter==20)
				{
					System.out.println("Optimal path is: "+pop.best.toString());
					break;
				}
			}
			try
			{
				Thread.sleep(250);
			} catch (Exception e ){
				e.printStackTrace();
			}
		}
	}
}
