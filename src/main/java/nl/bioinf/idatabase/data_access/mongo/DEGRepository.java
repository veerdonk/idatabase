package nl.bioinf.idatabase.data_access.mongo;

import nl.bioinf.idatabase.model.DEG;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by dvandeveerdonk on 12-6-17.
 */
public interface DEGRepository extends MongoRepository<DEG, String> {
    List<DEG> findDEGSByEnsId(String ensId);
    List<DEG> findDEGSByGeneName(String geneName);
    List<DEG> findDEGSByGeneNameStartingWith(String start);
}
