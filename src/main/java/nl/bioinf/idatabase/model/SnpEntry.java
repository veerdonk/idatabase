package nl.bioinf.idatabase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by dvandeveerdonk on 15-5-17.
 */
@Document
public class SnpEntry {
    @Id
    private String id;
    private List<SNP> snps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SNP> getSnps() {
        return snps;
    }

    public void setSnps(List<SNP> snps) {
        this.snps = snps;
    }

}
