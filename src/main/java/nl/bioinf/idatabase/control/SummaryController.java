package nl.bioinf.idatabase.control;

import com.google.gson.Gson;
import nl.bioinf.idatabase.model.StressFactor;
import nl.bioinf.idatabase.service.GeneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

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


        return "/summary";
    }
    @ResponseBody
    @RequestMapping(value = "/getPathogenData")
    public String pathogenData(Model model){

        List<StressFactor> numGenes = geneService.numberOfGenesPerVector();
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(StressFactor sf : numGenes){
            sb.append("{label: '");
            sb.append(sf.getOrganism() + sf.getTimepoint());
            sb.append("', y: ");
            sb.append(sf.getNumberOfGenes());
            sb.append("},\n");
        }
        sb.setLength(sb.length()-2);
        sb.append("\n]");

        Gson gson = new Gson();
        String numbers = gson.toJson(numGenes);
        System.out.println(sb.toString());

        return sb.toString();
    }
}
