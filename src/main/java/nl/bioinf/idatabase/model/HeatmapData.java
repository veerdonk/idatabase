package nl.bioinf.idatabase.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvandeveerdonk on 22-5-17.
 */
public class HeatmapData {
    private String qtl;
    private List<Double[]> z;
    private List<String> x;
    private List<String> y;

    @Override
    public String toString() {
        return "HeatmapData{" +
                "qtl='" + qtl + '\'' +
                ", z=" + z +
                ", x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                '}';
    }

    public String getQtl() {
        return qtl;
    }

    public void setQtl(String qtl) {
        this.qtl = qtl;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getX() {
        return x;
    }

    public void setX(List<String> x) {
        this.x = x;
    }

    public List<String> getY() {
        return y;
    }

    public void setY(List<String> y) {
        this.y = y;
    }

    public List<Double[]> getZ() {
        return z;
    }

    public void setZ(List<Double[]> z) {
        this.z = z;
    }
}
