import java.util.ArrayList;
import java.util.Collections;

public class Gene {
	private ArrayList<City> gene=new ArrayList<City>();
	
	/**
	 * Default constructor
	 */
	public Gene(){}
	
	/**
	 * Creates the gene from a list of cities ad randomizes them
	 * @param path original path, to be used
	 */
	public Gene(ArrayList<City> path)
	{
		for(City c : path)
		{
			gene.add(c);
		}
		this.shuffle();
	}
	
	/**
	 * Finds the distance of this gene
	 * @return Distance, lower is better
	 */
	public double distance()
	{
		double sum=0;
		for(int i = 0;i<gene.size()-1;i++)
		{
			sum+=gene.get(i).getDist(gene.get(i+1));
		}
		return sum;
	}
	
	/**
	 * iterable of the gene, allows iteration through it
	 * @return The Gene as a chromosome
	 */
	public Iterable<City> iterable()
	{
		return this.getArrayList();
	}
	
	/**
	 * Add data to the end of the gene
	 * @param data data to be added
	 */
	public boolean add(City data)
	{
		return gene.add(data);
	}
	
	/**
	 * Put data at index, replacing current data
	 * @param data data to add
	 * @param index where to add the data and remove the old data
	 * @return data previously at the index
	 */
	public City replace(City data, int index)
	{
		return gene.set(index, data);
	}
	
	/**
	 * Gets the data at an index
	 * @param index index to get data from
	 * @return data previously at 
	 */
	public City get(int index)
	{
		return gene.get(index);
	}
	
	/**
	 * Gives the size of the gene
	 * @return size of the gene
	 */
	public int size()
	{
		return gene.size();
	}
	
	/**
	 * Returns the arraylist of alleles
	 * @return arraylist of the alleles
	 */
	public ArrayList<City> getArrayList()
	{
		ArrayList<City> tmp = new ArrayList<City>();
		for(City c : gene)
		{
			tmp.add(c);
		}
		return tmp;
	}
	
	public void shuffle()
	{
		Collections.shuffle(gene);
	}
	
	/**
	 * String representation of the Gene
	 * @return String representation of the gene
	 */
	public String toString()
	{
		String ret = "{ ";
		for(City b : gene)
		{
			ret+=b.toString()+",";
		}
		return ret.substring(0, ret.length()-1)+", distance="+this.distance()+" }";
	}
	
	public boolean contains(City city)
	{
		return gene.contains(city);
	}
}
