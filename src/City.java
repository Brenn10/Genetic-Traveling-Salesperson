
public class City {
	public static int number=0;
	String name;
	double x,y;
	
	public City(double x, double y, String name)
	{
		this.x=x;
		this.y=y;
		this.name=name;
	}
	public double getY()
	{
		return y;
	}
	public double getX()
	{
		return x;
	}
	public double getDist(City other)
	{
		double oy=other.getY();
		double ox=other.getX();
		double dy=oy-y;
		double dx=ox-x;
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	public String getName()
	{
		return name;
	}
	
	public double[] coords()
	{
		double[] d ={x,y};
		return d;
	}
	
	public String toString()
	{
		//return "{ name="+name+",x="+x+",y="+y+" }";
		return name+" ";
	}
}
