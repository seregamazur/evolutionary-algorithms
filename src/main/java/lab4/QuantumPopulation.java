package lab4;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class QuantumPopulation
{
	private List<QuantumIndividual> individuals;
	private int gate;

	public QuantumPopulation(int size, int gate, boolean createNew)
	{
		individuals = new ArrayList<>();
		if (createNew)
		{
			for (int i = 0; i < size; i++)
			{
				individuals.add(new QuantumIndividual(gate));
			}
		}
	}

}
