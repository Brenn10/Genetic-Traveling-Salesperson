import java.util.ArrayList;

public class PopulationTester {
	public static void main(String[] args) {
		ArrayList<City> cities = new ArrayList<City>();
		cities.add(new City(540  , 19691 ,"First"));
		cities.add(new City(10715, 22304 ,"Second"));
		cities.add(new City(15340, 22447 ,"Third"));
		cities.add(new City(15371, 20407 ,"Fourth"));
		cities.add(new City(5913 , 6668  ,"Fifth"));
		cities.add(new City(15695, 19662 ,"Sixth"));
		cities.add(new City(661  , 13170 ,"Seventh"));
		Population pop = new Population(100, 0.001, 30,cities);
		System.out.println(pop.population.get(1));
		System.out.println(pop.population.get(2));
		System.out.println(pop.crossover(pop.population.get(1), pop.population.get(2), 3));
	}
}
