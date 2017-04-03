package nl.bioinf.idatabase.dataAccess;

import nl.bioinf.idatabase.model.Gene;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dvandeveerdonk on 6-3-17.
 * Interface for gene database
 */
public interface GeneDataSource {
    List<Gene> getGeneByensId(String ensId);
    List<Gene> getGeneByGeneName(String geneName);
    List<String> getNames(String query);
    HashMap numberOfGenesPerVector();

}