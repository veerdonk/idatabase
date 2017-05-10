package nl.bioinf.idatabase.model;

/**
 * Created by dvandeveerdonk on 8-5-17.
 * Describes the SNP object
 * used as a template for data from a database
 */
public class SNP {
    private int qtlId;
    private String snp;
    private String cell_type;
    private double pval;
    private String qtl_type;

    public String getQtl_type() {
        return qtl_type;
    }

    public void setQtl_type(String qtl_type) {
        this.qtl_type = qtl_type;
    }

    public String getCell_type() {
        return cell_type;
    }

    public void setCell_type(String cell_type) {
        this.cell_type = cell_type;
    }

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

    public double getPval() {
        return pval;
    }

    public void setPval(double pval) {
        this.pval = pval;
    }
}
