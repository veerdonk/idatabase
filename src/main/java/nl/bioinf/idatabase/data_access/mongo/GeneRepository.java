package nl.bioinf.idatabase.data_access.mongo;

import nl.bioinf.idatabase.model.DEGene;
import nl.bioinf.idatabase.model.Gene;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvandeveerdonk on 8-6-17.
 */
public interface GeneRepository extends MongoRepository<Gene, String> {

    List<DEGene> findGenesByGeneName(String geneName);
    List<DEGene> findGeneByEnsId(String ensId);
    List<DEGene> findGeneByGeneNameContaining(String query);
}
