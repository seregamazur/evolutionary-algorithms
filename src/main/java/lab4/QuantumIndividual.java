package lab4;

import lombok.Data;

@Data
public class QuantumIndividual
{
	private double[] propertyAs;
	private double[] propertyBs;
	private String data;
	private double dataDouble;
	private double fitness;

	public QuantumIndividual(int gate)
	{
		this.propertyAs = new double[gate];
		this.propertyBs = new double[gate];
		this.data = "";
	}
}
