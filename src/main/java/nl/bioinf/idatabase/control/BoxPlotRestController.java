package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.model.BoxplotEntry;
import nl.bioinf.idatabase.model.Gene;
import nl.bioinf.idatabase.service.GeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dvandeveerdonk on 3-5-17.
 */
@RestController
public class BoxPlotRestController {
    @Autowired
    GeneService geneService;

    @RequestMapping(value = "/search/boxplotData")
    public List<BoxplotEntry> boxplotEntries(@RequestParam("geneName") String geneName){
        List<Gene> genes = geneService.getGene(geneName);

        for(Gene gene:genes){
            BoxplotEntry boxplotEntry = new BoxplotEntry();
//            boxplotEntry.
        }
        System.out.println(genes);
        return null;
    }
}
