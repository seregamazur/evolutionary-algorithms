package lab2.model;

import lombok.Data;

@Data
public class Individual
{

	protected int defaultGeneLength = 15;
	private byte[] genes = new byte[defaultGeneLength];

	public Individual()
	{
		for (int i = 0; i < genes.length; i++)
		{
			byte gene = (byte) Math.round(Math.random());
			genes[i] = gene;
		}
	}

	public double getPrice(Knapsack knapsack)
	{
		double price = 0.0;
		for (int i = 0; i < genes.length; i++)
		{
			if (genes[i] == 1) price += knapsack.getItems().get(i).getPrice();
		}
		return price;
	}

	public double getWeight(Knapsack knapsack)
	{
		double weight = 0.0;
		for (int i = 0; i < genes.length; i++)
		{
			if (genes[i] == 1)
			{
				weight += knapsack.getItems().get(i).getWeight();
			}
		}
		return weight;
	}

	public byte getSingleGene(int index)
	{
		return genes[index];
	}

	public void setSingleGene(int index, byte value)
	{
		genes[index] = value;
	}

	@Override
	public String toString()
	{
		StringBuilder geneString = new StringBuilder();
		for (int i = 0; i < genes.length; i++)
		{
			geneString.append(getSingleGene(i));
		}
		return geneString.toString();
	}

}
