package nl.bioinf.idatabase.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvandeveerdonk on 22-5-17.
 */
public class HeatmapData {
    private List<Double[]> z;
    private ArrayList<String> x;
    private ArrayList<String> y;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getX() {
        return x;
    }

    public void setX(ArrayList<String> x) {
        this.x = x;
    }

    public ArrayList<String> getY() {
        return y;
    }

    public void setY(ArrayList<String> y) {
        this.y = y;
    }

    public List<Double[]> getZ() {
        return z;
    }

    public void setZ(List<Double[]> z) {
        this.z = z;
    }
}
