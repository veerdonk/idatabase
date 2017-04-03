package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.service.GeneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * Created by dvandeveerdonk on 29-3-17.
 * Controller for the summary page
 * uses geneService for data access
 */
@Controller
public class SummaryController {
    private final GeneService geneService;

    public SummaryController(GeneService geneService) {
        this.geneService = geneService;
    }

    @RequestMapping(value = "/{locale}/summary")
    public String summary(Model model){

        HashMap numGenes = geneService.numberOfGenesPerVector();
        System.out.println(numGenes);

        return "/summary";
    }
}
