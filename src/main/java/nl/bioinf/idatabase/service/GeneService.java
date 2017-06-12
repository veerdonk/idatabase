package nl.bioinf.idatabase.service;

import nl.bioinf.idatabase.data_access.GeneDataSource;
import nl.bioinf.idatabase.model.DEG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dvandeveerdonk on 8-3-17.
 * Service for connecting to a datasource
 * uses datasource implementation to retrieve information
 * from a database
 */
@Service
public class GeneService {
    private final GeneDataSource geneDataSource;

    @Autowired
    public GeneService(GeneDataSource geneDataSource) {
        this.geneDataSource = geneDataSource;
    }

    /**
     * Method for retriveing genes from the database
     * based on an identifier. checks what type of
     * identifier is given and uses the correct methods
     * @param identifier
     * @return a list of Gene objects
     */
    public List<DEG> getGene(String identifier){
        if(identifier.matches("ENSG\\d*")){
            return geneDataSource.getGeneByensId(identifier);

        }
        else{
            return geneDataSource.getGeneByGeneName(identifier);
        }
    }
    public List<String> getNames(String query){
        return geneDataSource.getNames(query);
    }

}
