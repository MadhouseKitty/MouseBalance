package de.kitty.saremox.mousebalance.materials;

public class Weight
{
	private final int _weight;

	public Weight(int weight)
	{
		if (weight < 0)
		{
			throw new NumberFormatException();
		}
		_weight = weight;
	}

	public int compareWeightTo(Weight one)
	{
		if (one.getWeight() > this.getWeight())
		{
			return 1;
		}

		else if (one.getWeight() < this.getWeight())
		{
			return -1;
		}

		return 0;
	}

	public int getWeight()
	{
		return _weight;
	}

	@Override
	public String toString()
	{
		return Integer.toString(_weight);
	}
}
