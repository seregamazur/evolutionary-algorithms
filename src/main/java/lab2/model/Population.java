package lab2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

@Data
public class Population
{

	private List<Individual> individuals;
	private Knapsack knapsack;

	public Population(int size, boolean createNew, Knapsack knapsack)
	{
		individuals = new ArrayList<>();
		this.knapsack = knapsack;

		if (createNew)
		{
			individuals = Stream.generate(Individual::new).limit(size).collect(Collectors.toList());
		}
	}

	public Individual getIndividual(int index)
	{
		return individuals.get(index);
	}

	public Individual getFittest()
	{
		Individual fittest = individuals.get(0);
		for (int i = 0; i < individuals.size(); i++)
		{
			if ((fittest.getPrice(knapsack) < getIndividual(i).getPrice(knapsack)))
			{
				fittest = this.getIndividual(i);
			}
		}
		return fittest;
	}

	public void fixWeakest()
	{
		List<Integer> sortedItems = knapsack.getSortedIds();
		for (Individual individual : individuals)
		{
			if (individual.getWeight(knapsack) > knapsack.getMaxWeight())
			{
				while (individual.getWeight(knapsack) > knapsack.getMaxWeight())
				{
					removeItem(individual.getGenes(), sortedItems);
				}
			}
		}
	}

	public void removeItem(byte[] bytes, List<Integer> sortedIds)
	{
		for (Integer sortedId : sortedIds)
		{
			if (bytes[sortedId] == 1)
			{
				bytes[sortedId] = 0;
				return;
			}
		}

	}

}
