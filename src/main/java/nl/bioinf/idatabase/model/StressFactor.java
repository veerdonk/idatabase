package nl.bioinf.idatabase.model;

/**
 * Created by dvandeveerdonk on 30-3-17.
 * Defines the properties of a stressfactor
 */
public class StressFactor {
    private String organism;
    private String timepoint;
    private int numberOfGenes;

    public StressFactor(String organism, String timepoint, int numberOfGenes) {
        this.organism = organism;
        this.timepoint = timepoint;
        this.numberOfGenes = numberOfGenes;
    }

    public String getOrganism() {
        return organism;
    }

    public String getTimepoint() {
        return timepoint;
    }

    public int getNumberOfGenes() {
        return numberOfGenes;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public void setTimepoint(String timepoint) {
        this.timepoint = timepoint;
    }

    public void setNumberOfGenes(int numberOfGenes) {
        this.numberOfGenes = numberOfGenes;
    }
}
