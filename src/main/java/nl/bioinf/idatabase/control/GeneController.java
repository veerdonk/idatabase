package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.model.Gene;
import nl.bioinf.idatabase.service.GeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by dvandeveerdonk on 8-3-17.\
 * Uses the geneservice to retrieve a gene with
 * the genename from the database. Gene to search for comes
 * from a webform
 */

@Controller
public class GeneController {

    /**
     * GeneService is used as intermediate for
     * connecting to a datasource
     */
    @Autowired
    GeneService geneService;

    private RestTemplate restTemplate;
    /**
     * Retrieves genes from a datasource using geneservice
     * checks whether there is a result and ties it to the
     * model.
     * @param model
     * @param id
     * @return reference to geneResults.html
     */
    @RequestMapping(value="/{locale}/gene")
    public String geneResults(Model model, @RequestParam("geneId") String id){
        if(id.matches("rs\\d*")){
            System.out.println(id);
            String response = restTemplate.getForObject("http://myvariant.info/v1/query?q={id}&fields=dbsnp", String.class, id);
            System.out.println(response);
        }

        List<Gene> genes = geneService.getGene(id);

        if(genes.isEmpty()){
            model.addAttribute("noSuchGene", true);
            return "/home";
        }



        model.addAttribute("geneName", id);
        model.addAttribute("genes", genes);

        return "/geneResults";
    }
}
