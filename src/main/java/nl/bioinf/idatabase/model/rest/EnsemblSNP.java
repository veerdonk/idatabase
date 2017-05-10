package nl.bioinf.idatabase.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by dvandeveerdonk on 8-5-17.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnsemblSNP {
    private String id;
    private int start;
    private int end;
    private List<String> alleles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<String> getAlleles() {
        return alleles;
    }

    public void setAlleles(List<String> alleles) {
        this.alleles = alleles;
    }
}
