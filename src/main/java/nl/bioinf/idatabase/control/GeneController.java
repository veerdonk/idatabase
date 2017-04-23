package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.model.Gene;
import nl.bioinf.idatabase.service.GeneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by dvandeveerdonk on 8-3-17.\
 * Uses the geneservice to retrieve a gene with
 * the genename from the database. Gene to search for comes
 * from a webform
 */

@Controller
public class GeneController {
    private final GeneService geneService;

    public GeneController(GeneService geneService) {
        this.geneService = geneService;
    }

    @RequestMapping(value="/{locale}/gene")
    public String geneResults(Model model, @RequestParam("geneId") String id){
        List<Gene> genes = geneService.getGene(id);
        model.addAttribute("genes", genes);
        return "/geneResults";
    }

    @RequestMapping(value = "/{locale}/snp")
    public String snpResults(Model model, @RequestParam("snpName") String snp){
        model.addAttribute("geneId", snp);
        return "redirect: /{locale}/gene";
    }
}
