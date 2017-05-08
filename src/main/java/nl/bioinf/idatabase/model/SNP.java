package nl.bioinf.idatabase.model;

/**
 * Created by dvandeveerdonk on 8-5-17.
 */
public class SNP {
    private int qtlId;
    private String snp;
    private String types;
    private double pval;

    public int getQtlId() {
        return qtlId;
    }

    public void setQtlId(int qtlId) {
        this.qtlId = qtlId;
    }

    public String getSnp() {
        return snp;
    }

    public void setSnp(String snp) {
        this.snp = snp;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public double getPval() {
        return pval;
    }

    public void setPval(double pval) {
        this.pval = pval;
    }
}
