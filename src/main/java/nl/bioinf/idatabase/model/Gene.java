package nl.bioinf.idatabase.model;

/**
 * Created by dvandeveerdonk on 8-3-17.
 * Gene object that can be filled with information from a database
 */

public class Gene {
    private int id;
    private String stressFactor;
    private String timepoint;
    private String ensId;
    private double log2fold;
    private double pval;
    private String geneName;

    public Gene(int id, String stressFactor, String timepoint, String ensId, double log2fold, double pval, String geneName) {
        this.id = id;
        this.stressFactor = stressFactor;
        this.timepoint = timepoint;
        this.ensId = ensId;
        this.log2fold = log2fold;
        this.pval = pval;
        this.geneName = geneName;
    }

    public int getId() {
        return id;
    }

    public String getStressFactor() {
        return stressFactor;
    }

    public String getTimepoint() {
        return timepoint;
    }

    public String getEnsId() {
        return ensId;
    }

    public double getLog2fold() {
        return log2fold;
    }

    public double getPval() {
        return pval;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStressFactor(String stressFactor) {
        this.stressFactor = stressFactor;
    }

    public void setTimepoint(String timepoint) {
        this.timepoint = timepoint;
    }

    public void setEnsId(String ensId) {
        this.ensId = ensId;
    }

    public void setLog2fold(double log2fold) {
        this.log2fold = log2fold;
    }

    public void setPval(double pval) {
        this.pval = pval;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }
}

