package nl.bioinf.idatabase.data_access.mongo;

import nl.bioinf.idatabase.data_access.GeneDataSource;
import nl.bioinf.idatabase.model.DEG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvandeveerdonk on 12-6-17.
 */
@Component
public class GeneDataSourceMongo implements GeneDataSource{

    @Autowired
    DEGRepository degRepository;

    @Override
    public List<DEG> getGeneByensId(String ensId) {
        return degRepository.findDEGSByEnsId(ensId);
    }

    @Override
    public List<DEG> getGeneByGeneName(String geneName) {
        return degRepository.findDEGSByGeneName(geneName);
    }

    @Override
    public List<String> getNames(String query) {
        List<DEG> degs = degRepository.findDEGSByGeneNameStartingWith(query);
        ArrayList<String> names = new ArrayList<>();
        for(DEG deg : degs){
            if(!names.contains(deg.getGeneName())){
                names.add(deg.getGeneName());
            }
        }
        return names;
    }
}
