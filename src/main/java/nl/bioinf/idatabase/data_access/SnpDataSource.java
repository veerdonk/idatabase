package nl.bioinf.idatabase.data_access;

import nl.bioinf.idatabase.model.SnpEntry;

import java.util.List;

/**
 * Created by dvandeveerdonk on 8-5-17.
 */
public interface SnpDataSource {
    SnpEntry getSnpById(String snpId);
    List<SnpEntry> getSnpByGeneName(String geneName, int region);
}
