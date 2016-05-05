import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Driver {
	public static void main(String[] args) throws IOException {
		ArrayList<City> cities = new ArrayList<City>();
		for(int i = 0; i< 20; ++i)
		{
			cities.add(new City(i*5647563456L%4675643567L+1, 600, 900, "R"+i));
		}
				
		
		Population pop = new Population(5000, .003, 1000,cities);
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
				if(counter==20)
				{
					model.solved(pop.best);
					System.out.println("Optimal path is: "+pop.best.toString());
					break;
				}
			}
			model.update(pop.best,i,counter);
		}
	}
}