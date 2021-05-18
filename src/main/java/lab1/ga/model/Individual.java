package lab1.ga.model;

import lombok.Data;

@Data
public class Individual {

    protected int defaultGeneLength = 8;
    private byte[] genes = new byte[defaultGeneLength];

    public Individual() {
        for (int i = 0; i < genes.length; i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }

    public int getValue() {
        StringBuilder builder = new StringBuilder();
        for (byte value : genes) {
            builder.append(value);
        }
        return Integer.parseInt(builder.toString(), 2);
    }

    public double getFx() {
        return Math.pow(-(2 * Math.pow(getValue(), 2) - 5 * getValue() + 13), 2) / 256;
    }

    public double getFnorm(Population population) {
        return getFx() / population.getIndividuals().stream().mapToDouble(Individual::getFx).sum();
    }

    public byte getSingleGene(int index) {
        return genes[index];
    }

    public void setSingleGene(int index, byte value) {
        genes[index] = value;
    }

    @Override
    public String toString() {
        StringBuilder geneString = new StringBuilder();
        for (int i = 0; i < genes.length; i++) {
            geneString.append(getSingleGene(i));
        }
        return geneString.toString();
    }

}
