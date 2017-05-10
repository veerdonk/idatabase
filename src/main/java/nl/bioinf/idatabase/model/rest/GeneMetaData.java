package nl.bioinf.idatabase.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by dvandeveerdonk on 10-5-17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneMetaData {
    private String id;
    private String species;
    private int seq_region_name;
    private int start;
    private int end;
    private String description;
    private int numberOfSnps;
    private List<EnsemblSNP> snps;

    public int getNumberOfSnps() {
        return numberOfSnps;
    }

    public void setNumberOfSnps(int numberOfSnps) {
        this.numberOfSnps = numberOfSnps;
    }

    public List<EnsemblSNP> getSnps() {
        return snps;
    }

    public void setSnps(List<EnsemblSNP> snps) {
        this.snps = snps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getSeq_region_name() {
        return seq_region_name;
    }

    public void setSeq_region_name(int seq_region_name) {
        this.seq_region_name = seq_region_name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
