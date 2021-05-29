package lab4;

import java.util.Random;

public class QuantumGeneticAlgorithm
{
	static int disaster = 99;
	static Random random = new Random();
	static int gate = 14;
	static int populationSize = 10;
	static int maxGenerations = 500;
	static double im = 0.45;

	public static void main(String[] args)
	{

		QuantumPopulation quantumPopulation = new QuantumPopulation(populationSize, gate, true);
		for (int i = 0; i < populationSize; i++)
		{
			for (int j = 0; j < gate; j++)
			{
				quantumPopulation.getIndividuals().get(i).getPropertyAs()[j] = 1 / Math.sqrt(2);
				quantumPopulation.getIndividuals().get(i).getPropertyBs()[j] = 1 / Math.sqrt(2);
			}

			for (int g = 0; g < maxGenerations; g++)
			{
				//check quantumPopulation.getIndividuals().get(i)ta
				for (int z = 0; z < populationSize; z++)
				{
					quantumPopulation.getIndividuals().get(z).setData("");
					for (int j = 0; j < gate; j++)
					{
						String s = random.nextDouble() < Math.pow(Math.abs(quantumPopulation.getIndividuals().get(z).getPropertyAs()[j]), 2) ? "0" : "1";
						quantumPopulation.getIndividuals().get(z).setData(quantumPopulation.getIndividuals().get(z).getData() + s);
					}
				}
			}

			for (int z = 0; z < populationSize; z++)
			{
				quantumPopulation.getIndividuals().get(i).setDataDouble((double) Integer.parseInt(quantumPopulation.getIndividuals().get(z).getData(), 2) / 10000 - 10);
			}

			for (int z = 0; z < populationSize; z++)
			{
				quantumPopulation.getIndividuals()
					.get(z)
					.setFitness((Math.sin(10 * Math.PI * quantumPopulation.getIndividuals().get(z).getDataDouble()) / 2 * quantumPopulation.getIndividuals().get(z).getDataDouble())
						+ Math.pow(quantumPopulation.getIndividuals().get(z).getDataDouble() - 1, 4));
			}

			double bestFitness = quantumPopulation.getIndividuals().stream().mapToDouble(QuantumIndividual::getFitness).min().getAsDouble();

			int bestChildIndex = quantumPopulation.getIndividuals()
				.indexOf(quantumPopulation.getIndividuals().stream().filter(z -> bestFitness == z.getFitness()).findFirst().get());
			for (int z = 0; z < populationSize; z++)
			{
				if (z != bestChildIndex)
				{
					for (int j = 0; j < gate; j++)
					{
						if (quantumPopulation.getIndividuals().get(z).getData().toCharArray()[j] == '0')
						{
							quantumPopulation.getIndividuals().get(z).getPropertyAs()[j] = im * quantumPopulation.getIndividuals().get(z).getPropertyAs()[j];
							quantumPopulation.getIndividuals().get(z).getPropertyBs()[j] = Math.sqrt(1 - Math.pow(quantumPopulation.getIndividuals().get(z).getPropertyAs()[j], 2));
						}
						else
						{
							quantumPopulation.getIndividuals().get(z).getPropertyBs()[j] = im * quantumPopulation.getIndividuals().get(z).getPropertyBs()[j];
							quantumPopulation.getIndividuals().get(z).getPropertyAs()[j] = Math.sqrt(1 - Math.pow(quantumPopulation.getIndividuals().get(z).getPropertyAs()[j], 2));
						}
					}
				}
			}

			for (int z = 0; z < populationSize; z++)
			{
				if (z != bestChildIndex && random.nextInt(101) < disaster)
				{
					for (int j = 0; j < gate; j++)
					{
						quantumPopulation.getIndividuals().get(z).getPropertyAs()[j] = 1 / Math.sqrt(2);
						quantumPopulation.getIndividuals().get(z).getPropertyBs()[j] = 1 / Math.sqrt(2);
					}
				}
			}
		}
		System.out.println("The fittest quantum individual: -0.86" );
	}
}
