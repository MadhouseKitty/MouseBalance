package MouseBalance.materials;

public class Weight
{
	private final int _weight;
	
	public Weight(int weight)
	{
		_weight = weight;
	}
	
	public int getWeight()
	{
		return _weight;
	}	
	
	public int compareWeightTo(Weight one)
	{
		if(one.getWeight() > this.getWeight())
		{
			return 1;
		}	
		
		else if (one.getWeight() < this.getWeight())
		{
			return -1;
		}	
		
		return 0;
	}
}
