import java.util.ArrayList;
import java.util.Random;

public class Population {
	public  ArrayList<Gene> population=new ArrayList<Gene>();
	private int popSize;
	private double mutationRate;
	private int tourneySize;
	public  Gene best;
	private Random rand = new Random();
	private ArrayList<City> ogCities=new ArrayList<City>();
	
	/**
	 * Creates the first generation of the population
	 * @param sizeOfPop number of individuals in the population
	 * @param mutationRate probability of a point mutation to occur
	 * @param sizeOfGene number of alleles in each gene
	 * @param tourneySize number of individuals to compete for mating
	 */
	public Population(int sizeOfPop, double mutationRate,int tourneySize,ArrayList<City> originalCities)
	{
		for(City c : originalCities)
		{
			ogCities.add(c);
		}
		this.popSize=sizeOfPop;
		this.mutationRate=mutationRate;
		this.tourneySize=tourneySize;
		populate();

		best=population.get(0);
	}
	
	/**
	 * Creates generation one of the population
	 * @param random true to initialize randomly
	 */
	public void populate()
	{
		while(population.size()<popSize)
		{
			Gene newgene = new Gene(ogCities);
			newgene.shuffle();
			population.add(newgene);
		}
	}
	
	/**
	 * Evolve one generation
	 * @return highest fitness
	 */
	public void evolve()
	{
		//Make a new population with the non dead population as the parents
		ArrayList<Gene> newPop = new ArrayList<Gene>(popSize);
		
		/*
		 * Mate the winners of tournaments to create a new generation
		 * Use crossover mutation to pass genes
		 */
		while(newPop.size()<popSize)
		{
			Gene g1= tournament();
			Gene g2 = tournament();
			while(g2.equals(g1))
			{
				g2=tournament();
			}
			int index=rand.nextInt(g1.size());
			newPop.add(bestGene(crossover(g1, g2, index),crossover(g2, g1, index)));
		}
		
		//place the new population in the place of the old
		population=newPop;
		
		//Perform random point mutations
		mutate();

	}
	
	/**
	 * Selects the best Gene from a group
	 * @return Best gene in a group
	 */
	public Gene tournament()
	{
		ArrayList<Gene> members =  new ArrayList<Gene>(tourneySize);
		while(members.size()<tourneySize)
		{
			Gene next = population.get(rand.nextInt(population.size()));
			while(members.contains(next))
			{
				next = population.get(rand.nextInt(population.size()));
			}
			members.add(next);
		}
		return bestGene(members);
	}
	
	/**
	 * performs the swap mutations
	 */
	public void mutate()
	{
		for(int i = 0; i < population.size();++i)
		{
			Gene g = population.get(i);
			for(int u =0; u < g.size();++u)
			{
				if(rand.nextDouble()<mutationRate)
				{
					int other;
					if(u+1==g.size())
						other=u-1;
					else
						other=u+1;
					City c = g.replace(g.get(u), other);
					g.replace(c, u);
				}
			}
		}
	}
	
	/**
	 * Performs a crossover mutation
	 * @param gene1 First gene to cross
	 * @param gene2 Second gene to Cross
	 * @param index where to cross at
	 * @return new gene created from the cross
	 */
	public Gene crossover(Gene gene1, Gene gene2, int index)
	{
		Gene gene = new Gene();
		for(int i =0; i<index;++i)
		{
			gene.add(gene1.get(i));
		}
		for(City c : gene2.getArrayList())
		{
			if(!gene.contains(c))
				gene.add(c);
		}
		return gene;
	}
	
	/**
	 * Gives the best fitness
	 * @return best fitness
	 */
	public double bestFitness()
	{
		Gene lowest=population.get(0);
		for(Gene g : population)
		{
			if(g.distance()<lowest.distance())
			{
				lowest=g;
			}
		}
		if(best.distance()>lowest.distance())
			best=lowest;
		return lowest.distance();
	}
	
	/**
	 * returns the gene with the lowest distance in a group
	 * @param members genes to compete against each other
	 * @return the gene who kills all of the others. JK, just the coolest dude
	 */
	private Gene bestGene(ArrayList<Gene> members)
	{
		Gene g = members.remove(0);
		while(!members.isEmpty())
		{
			Gene g2 = members.remove(0);
			if(g2.distance()<g.distance())
			{
				g=g2;
			}
		}
		return g;
	}
	
	private Gene bestGene(Gene g1, Gene g2)
	{
		if(g1.distance()<g2.distance())
			return g1;
		return g2;
	}
	
	/**
	 * Gives the average fitness of the generation
	 * @return average fitness
	 */
	public double average()
	{
		long sum=0;
		for(Gene g : population)
		{
			sum+=g.distance();
		}
		return (sum)/(double)population.size();
	}
	
	public void reset()
	{
		population.clear();
		
		populate();

		best=population.get(0);
	}
	
	/**
	 * Gives the population as a String
	 *  @return String representation of the population
	 */
	public String toString()
	{
		String ret="{ ";
		for(Gene g : population)
		{
			ret+=g.toString()+":"+g.distance()+" ";
		}
		return ret+" }";
	}
}
