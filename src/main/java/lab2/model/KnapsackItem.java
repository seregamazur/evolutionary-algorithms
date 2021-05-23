package lab2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KnapsackItem
{
	int id;
	double price;
	double weight;
}
