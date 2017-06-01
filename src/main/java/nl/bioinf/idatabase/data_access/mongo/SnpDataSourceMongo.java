package nl.bioinf.idatabase.data_access.mongo;

import nl.bioinf.idatabase.control.SnpRestController;
import nl.bioinf.idatabase.data_access.SnpDataSource;
import nl.bioinf.idatabase.model.SnpEntry;
import nl.bioinf.idatabase.model.rest.EnsemblSNP;
import nl.bioinf.idatabase.model.rest.GeneMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvandeveerdonk on 15-5-17.
 * Implementation of SnpDataSource using MongoDb
 * Connects to a mongo database and performs queries
 */
@Component
public class SnpDataSourceMongo implements SnpDataSource {

    @Autowired
    SnpRepository snpRepository;

    @Autowired
    SnpRestController snpRestController;

    /**
     * Used to retrieve all info associated with
     * a single snp id from the database.
     * uses the SnpRepository interface
     * @param snpId
     * @return SnpEntry object
     */
    @Override
    public SnpEntry getSnpById(String snpId) {
        return snpRepository.findSnpById(snpId);
    }

    /**
     * Given a gene name this method uses the SnpRestController to
     * find all known human snps within that gene and the given region
     * around it.
     * These snps are then queried against the database to find matches
     * all matches are returned as a list of SnpEntry objects
     * @param geneName
     * @param region
     * @return List of SnpEntry objects
     */
    @Override
    public List<SnpEntry> getSnpByGeneName(String geneName, int region) {
        GeneMetaData snpsOnGene = snpRestController.snps(geneName, region);
        List<SnpEntry> snpsFromDb = new ArrayList<>();
        for(EnsemblSNP snp:snpsOnGene.getSnps()){
            SnpEntry mongoSnp = getSnpById(snp.getId());
            if(mongoSnp != null){
                snpsFromDb.add(mongoSnp);
            }
        }
        return snpsFromDb;
    }
}
