package nl.bioinf.idatabase.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dvandeveerdonk on 8-5-17.
 * Template class for ensembl id and celltype
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnsemblId {
    private String type;
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
