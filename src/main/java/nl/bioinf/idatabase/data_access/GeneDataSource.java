package nl.bioinf.idatabase.data_access;

import nl.bioinf.idatabase.model.DEG;

import java.util.List;

/**
 * Created by dvandeveerdonk on 6-3-17.
 * Interface for gene database
 */
public interface GeneDataSource {
    List<DEG> getGeneByensId(String ensId);
    List<DEG> getGeneByGeneName(String geneName);
    List<String> getNames(String query);
}