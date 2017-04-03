package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.service.GeneService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * Created by dvandeveerdonk on 23-3-17.
 * Controller for autocomplete ajax requests
 * uses geneservice to search the database
 * and returns a String specifically formatted
 * to work with devbridge autocomplete
 */

@Controller
public class AutocompleteController {
    private final GeneService geneService;

    public AutocompleteController(GeneService geneService) {
        this.geneService = geneService;
    }

    @ResponseBody
    @RequestMapping("/search/forGene")
    public String getGeneByNameOrId(String query){
        List<String> names = geneService.getNames(query);

        StringBuilder sb = new StringBuilder();

        sb.append("{\n\"suggestions\": [\n");

        for(String name:names){
            sb.append("{\"value\": \"");
            sb.append(name);
            sb.append("\"},\n");
        }
        sb.setLength(sb.length() - 2);
        sb.append("\n]\n}");

        return sb.toString();
    }
}
