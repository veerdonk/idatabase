package nl.bioinf.idatabase.model;

import java.util.List;

/**
 * Created by dvandeveerdonk on 8-6-17.
 */
public class GeneTableData {
    private List<Gene> data;

    public List<Gene> getData() {
        return data;
    }

    public void setData(List<Gene> data) {
        this.data = data;
    }
}
