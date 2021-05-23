package lab2;

import lab2.model.Individual;
import lab2.model.Knapsack;
import lab2.model.Population;

public class GeneticAlgorithm
{

	private static final double uniformRate = 0.65;
	private static final double mutationRate = 0.025;
	private static final int tournamentSize = 15;
	static final Knapsack knapsack = Knapsack.newBuilder().init();

	public static void main(String[] args)
	{
		new GeneticAlgorithm().runAlgorithm(8);
	}

	public void runAlgorithm(int populationSize)
	{
		Population population = new Population(populationSize, true, knapsack);
		printInfo(population);
		for (int i = 0; i < 101; i++)
		{
			population = enhancePopulation(population, knapsack);
			if (i == 100)
			{
				System.out.printf("Generation #%d\n", i);
				printInfo(population);
			}
		}
		System.out.println("The fittest gen: " + population.getFittest() + ". Weight = "
			+ population.getFittest().getWeight(knapsack) + ". Price = " + population.getFittest().getPrice(knapsack));
	}

	public Population enhancePopulation(Population oldPopulation, Knapsack knapsack)
	{
		Population newPopulation = new Population(oldPopulation.getIndividuals().size(), false, knapsack);

		for (int i = 0; i < oldPopulation.getIndividuals().size(); i++)
		{
			Individual i1 = tournamentSelection(oldPopulation);
			Individual i2 = tournamentSelection(oldPopulation);
			Individual newIndividual = crossover(i1, i2);
			newPopulation.getIndividuals().add(i, newIndividual);
		}

		for (int i = 0; i < newPopulation.getIndividuals().size(); i++)
		{
			mutate(newPopulation.getIndividual(i));
		}
		newPopulation.fixWeakest();

		return newPopulation;
	}

	private Individual crossover(Individual i1, Individual i2)
	{
		Individual newIndividual = new Individual();
		for (int i = 0; i < newIndividual.getDefaultGeneLength(); i++)
		{
			if (Math.random() <= uniformRate)
			{
				newIndividual.setSingleGene(i, i1.getSingleGene(i));
			}
			else
			{
				newIndividual.setSingleGene(i, i2.getSingleGene(i));
			}
		}
		return newIndividual;
	}

	private void mutate(Individual individual)
	{
		for (int i = 0; i < individual.getDefaultGeneLength(); i++)
		{
			if (Math.random() <= mutationRate)
			{
				byte gene = (byte) Math.round(Math.random());
				individual.setSingleGene(i, gene);
			}
		}
	}

	private Individual tournamentSelection(Population population)
	{
		Population tournament = new Population(tournamentSize, false, knapsack);
		for (int i = 0; i < tournamentSize; i++)
		{
			int randomId = (int) (Math.random() * population.getIndividuals().size());
			tournament.getIndividuals().add(i, population.getIndividual(randomId));
		}
		return tournament.getFittest();
	}

	private void printInfo(final Population population)
	{
		population.getIndividuals().forEach(individual ->
			System.out.println("Individual: " + individual +
				". Weight = " + individual.getWeight(knapsack) + ". Price = " + individual.getPrice(knapsack)));
	}

}
