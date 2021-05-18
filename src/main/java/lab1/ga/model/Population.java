package lab1.ga.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

@Data
public class Population {

    private List<Individual> individuals;

    public Population(int size, boolean createNew) {
        individuals = new ArrayList<>();
        if (createNew) {
            individuals = Stream.generate(Individual::new).limit(size).collect(Collectors.toList());
        }
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public Individual getFittest() {
        Individual fittest = individuals.get(0);
        for (int i = 0; i < individuals.size(); i++) {
            if (fittest.getFx() <= getIndividual(i).getFx()) {
                fittest = this.getIndividual(i);
            }
        }
        return fittest;
    }
}
