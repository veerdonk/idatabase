package nl.bioinf.idatabase.service;

import nl.bioinf.idatabase.data_access.SnpDataSource;
import nl.bioinf.idatabase.model.SNP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dvandeveerdonk on 8-5-17.
 */
@Service
public class SnpService {
    @Autowired
    SnpDataSource snpDataSource;

    public List<SNP> getSnps(String id){
        snpDataSource.getSnpById(id);
        return null;
    }

}
