package nl.bioinf.idatabase.model;

/**
 * Created by dvandeveerdonk on 8-5-17.
 * Describes the SNP object
 * used as a template for data from a database
 */
public class SNP {
    private int qtlId;
    private String snp;
    private String types;
    private double pval;

    public int getQtlId() {
        return qtlId;
    }

    @Override
    public String toString() {
        return "SNP{" +
                "qtlId=" + qtlId +
                ", snp='" + snp + '\'' +
                ", types='" + types + '\'' +
                ", pval=" + pval +
                '}';
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
