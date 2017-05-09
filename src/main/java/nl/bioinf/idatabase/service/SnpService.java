package nl.bioinf.idatabase.service;

import nl.bioinf.idatabase.data_access.SnpDataSource;
import nl.bioinf.idatabase.model.SNP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dvandeveerdonk on 8-5-17.
 * Service used to connect ro a datasource
 * uses the datasources implementation to
 * retrieve information about snps
 */
@Service
public class SnpService {

    @Autowired
    SnpDataSource snpDataSource;

    public Map<String, List<SNP>> getSnps(String id){
        return snpDataSource.getSnpById(id);
    }

    public Map<String, List<SNP>> getSnpByGene(String geneName){
        return snpDataSource.getSnpByGeneName(geneName);
    }

}
