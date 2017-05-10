package nl.bioinf.idatabase.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dvandeveerdonk on 8-5-17.
 * Template class to store information from ensembl
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnsemblGene {
    private String id;
    private int start;
    private int end;
    private int seq_region_name;

    public int getSeq_region_name() { return seq_region_name; }

    public void setSeq_region_name(int seq_region_name) {
        this.seq_region_name = seq_region_name;
    }

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
}
