package nl.bioinf.idatabase.service;

import nl.bioinf.idatabase.dataAccess.GeneDataSource;
import nl.bioinf.idatabase.model.Gene;
import nl.bioinf.idatabase.model.StressFactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dvandeveerdonk on 8-3-17.
 * Service for connecting to a datasource
 */
@Service
public class GeneService {
    private final GeneDataSource geneDataSource;

    @Autowired
    public GeneService(GeneDataSource geneDataSource) {
        this.geneDataSource = geneDataSource;
    }

    public List<Gene> getGene(String identifier){
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

    public List<StressFactor> numberOfGenesPerVector(){
        return geneDataSource.numberOfGenesPerVector();
    }

}
