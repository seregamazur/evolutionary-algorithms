package lab2.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Knapsack
{
	List<KnapsackItem> items = new ArrayList<>();
	int maxItems;
	double maxWeight;
	double maxPrice;

	public List<Integer> getSortedIds()
	{
		return items.stream()
			.sorted(Comparator.comparing(KnapsackItem::getPrice))
			.map(KnapsackItem::getId)
			.collect(Collectors.toList());
	}

	public static Builder newBuilder()
	{
		return new Knapsack.Builder();
	}

	public static class Builder
	{
		public Knapsack init()
		{
			Knapsack knapsack = new Knapsack();
			try
			{
				Scanner input = new Scanner(new File("/home/serhii/Documents/GIT/evolutionary-algorithm/src/main/java/lab2/resources/knapsack/f5_l-d_kp_15_375.txt"));
				knapsack.setMaxItems(input.nextInt());
				knapsack.setMaxWeight(input.nextInt());
				knapsack.setItems(IntStream.range(0, knapsack.getMaxItems())
					.mapToObj(i -> new KnapsackItem(i, input.nextDouble(), input.nextDouble()))
					.collect(Collectors.toList()));

				Scanner input1 = new Scanner(new File("/home/serhii/Documents/GIT/evolutionary-algorithm/src/main/java/lab2/resources/knapsack_optimum/f5_l-d_kp_15_375.txt"));
				knapsack.setMaxPrice(input1.nextDouble());
			}
			catch (FileNotFoundException ex)
			{
				System.err.println("File not found!");
				System.exit(1);
			}
			return knapsack;
		}
	}
}
