package nl.bioinf.idatabase.data_access.mongo;

import nl.bioinf.idatabase.model.SnpEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dvandeveerdonk on 15-5-17.
 */
@Repository
public interface SnpRepository extends MongoRepository<SnpEntry, String> {
    SnpEntry findSnpById(String snpId);
}
