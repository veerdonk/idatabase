//package nl.bioinf.idatabase.data_access.mongo;
//
//import nl.bioinf.idatabase.data_access.GeneDataSource;
//import nl.bioinf.idatabase.model.DEGene;
//import nl.bioinf.idatabase.model.Gene;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by dvandeveerdonk on 8-6-17.
// */
//@Component
//public class GeneDataSourceMongo implements GeneDataSource {
//
//    @Autowired
//    GeneRepository geneRepository;
//
//    @Override
//    public List<DEGene> getGeneByensId(String ensId) {
//        return geneRepository.findGeneByEnsId(ensId);
//    }
//
//    @Override
//    public List<DEGene> getGeneByGeneName(String geneName) {
//        System.out.println("trying to find: "+geneName);
//        return geneRepository.findGenesByGeneName(geneName);
//    }
//
//    @Override
//    public List<String> getNames(String query) {
//        System.out.println("trying " + query);
//        List<DEGene> genes = geneRepository.findGeneByGeneNameContaining(query);
//        System.out.println(genes);
//        ArrayList<String> names = new ArrayList<>();
//        for(DEGene gene:genes){
//            names.add(gene.getGeneName());
//        }
//        return names;
//    }
//}
