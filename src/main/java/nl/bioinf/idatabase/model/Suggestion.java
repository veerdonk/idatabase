package nl.bioinf.idatabase.model;

/**
 * Created by dvandeveerdonk on 13-6-17.
 */
public class Suggestion {
    private String value;

    public Suggestion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
