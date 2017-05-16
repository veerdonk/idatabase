package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.service.SnpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dvandeveerdonk on 29-3-17.
 * Controller for the summary page
 * uses geneService for data access
 */
@Controller
public class SummaryController {

    /**
     * listens for summary page request
     * @return reference to the summary.html template
     */
    @RequestMapping(value = "/{locale}/summary")
    public String summary(){
        return "/summary";
    }
}
