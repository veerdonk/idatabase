package nl.bioinf.idatabase.model;

/**
 * Created by dvandeveerdonk on 12-6-17.
 */
public class DEG {
    private String stressFactor;
    private String timePoint;
    private String ensId;
    private double log2fold;
    private double pval;
    private String geneName;

    @Override
    public String toString() {
        return "DEG{" +
                "stressFactor='" + stressFactor + '\'' +
                ", timePoint='" + timePoint + '\'' +
                ", ensId='" + ensId + '\'' +
                ", log2fold=" + log2fold +
                ", pval=" + pval +
                ", geneName='" + geneName + '\'' +
                '}';
    }

    public DEG(String stressFactor, String timePoint, String ensId, double log2fold, double pval, String geneName) {
        this.stressFactor = stressFactor;
        this.timePoint = timePoint;
        this.ensId = ensId;
        this.log2fold = log2fold;
        this.pval = pval;
        this.geneName = geneName;
    }

    public String getStressFactor() {
        return stressFactor;
    }

    public void setStressFactor(String stressFactor) {
        this.stressFactor = stressFactor;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public String getEnsId() {
        return ensId;
    }

    public void setEnsId(String ensId) {
        this.ensId = ensId;
    }

    public double getLog2fold() {
        return log2fold;
    }

    public void setLog2fold(double log2fold) {
        this.log2fold = log2fold;
    }

    public double getPval() {
        return pval;
    }

    public void setPval(double pval) {
        this.pval = pval;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }
}
