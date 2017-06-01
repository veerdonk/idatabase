package nl.bioinf.idatabase.model;

/**
 * Created by dvandeveerdonk on 8-5-17.
 * Describes the SNP object
 * used as a template for data from a database
 */

public class SNP {
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

    @Override
    public String toString() {
        return "SNP{" +
                "snp='" + snp + '\'' +
                ", cell_type='" + cell_type + '\'' +
                ", pval=" + pval +
                ", qtl_type='" + qtl_type + '\'' +
                '}';
    }
}
