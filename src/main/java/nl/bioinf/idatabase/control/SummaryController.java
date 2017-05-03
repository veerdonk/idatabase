package nl.bioinf.idatabase.control;

//import com.google.gson.Gson;
import nl.bioinf.idatabase.model.ChartTemplate;
import nl.bioinf.idatabase.model.StressFactor;
import nl.bioinf.idatabase.service.GeneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    @RequestMapping(value = "/getPathogenData", produces = "application/json")
    public List<ChartTemplate> pathogenData(Model model){

        List<StressFactor> numGenes = geneService.numberOfGenesPerVector();
        List<ChartTemplate> chartData = new ArrayList<>();
        for(StressFactor sf : numGenes){
            ChartTemplate chartTemplate = new ChartTemplate();
            chartTemplate.setLabel(sf.getOrganism()+sf.getTimepoint().toString());
            chartTemplate.setY(sf.getNumberOfGenes());
            chartData.add(chartTemplate);

        }


        return chartData;
    }
}
