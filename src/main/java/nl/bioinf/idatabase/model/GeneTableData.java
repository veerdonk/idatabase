package nl.bioinf.idatabase.model;

import java.util.List;

/**
 * Created by dvandeveerdonk on 8-6-17.
 */
public class GeneTableData {
    private List<DEG> data;

    public List<DEG> getData() {
        return data;
    }

    public void setData(List<DEG> data) {
        this.data = data;
    }
}
