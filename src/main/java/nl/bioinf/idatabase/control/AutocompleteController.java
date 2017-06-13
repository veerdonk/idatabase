package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.model.AutocompleteData;
import nl.bioinf.idatabase.model.Suggestion;
import nl.bioinf.idatabase.service.GeneService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    /**
     * uses geneService to retrieve a list of
     * gene objects from a datasource. then
     * formats these to work with devbridge
     * autocomplete.
     * @param query
     * @return devbridge compatible json
     */
    @ResponseBody
    @RequestMapping("/search/forGene")
    public AutocompleteData getGeneByNameOrId(String query){
        List<String> names = geneService.getNames(query);

        AutocompleteData autocompleteData = new AutocompleteData();
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        for(String name:names){
            Suggestion suggestion = new Suggestion(name);
            suggestions.add(suggestion);
        }
        autocompleteData.setSuggestions(suggestions);

        return autocompleteData;
    }
}
