package nl.bioinf.idatabase.data_access;

import nl.bioinf.idatabase.model.SNP;

import java.util.List;
import java.util.Map;

/**
 * Created by dvandeveerdonk on 8-5-17.
 */
public interface SnpDataSource {
    Map getSnpById(String snpId);
    List<SNP> getSnpByGeneName(String geneName);
}
