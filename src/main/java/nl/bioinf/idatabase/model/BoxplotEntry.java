package nl.bioinf.idatabase.model;

import java.util.List;

/**
 * Created by dvandeveerdonk on 3-5-17.
 */
public class BoxplotEntry {
    private List<Double> x;
    private String type;
    private String name;

    public List<Double> getX() {
        return x;
    }

    public void setX(List<Double> x) {
        this.x = x;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
