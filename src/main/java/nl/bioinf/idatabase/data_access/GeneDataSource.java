package nl.bioinf.idatabase.data_access;

import nl.bioinf.idatabase.model.DEGene;
import nl.bioinf.idatabase.model.Gene;

import java.util.List;

/**
 * Created by dvandeveerdonk on 6-3-17.
 * Interface for gene database
 */
public interface GeneDataSource {
    List<Gene> getGeneByensId(String ensId);
    List<Gene> getGeneByGeneName(String geneName);
    List<String> getNames(String query);
}