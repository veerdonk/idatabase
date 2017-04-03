package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.service.GeneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * Created by dvandeveerdonk on 8-3-17.
 * Maps the requests for the home page
 * adds the current localization to the url
 * eg /en/home.html
 */

@Controller
public class HomeController {
    private final GeneService geneService;

    public HomeController(GeneService geneService) {
        this.geneService = geneService;
    }

    @RequestMapping(
            value = {"", "/", "home", "/home", "/home.html"})
            public String home(Locale locale){
        return "redirect:" + locale.getLanguage() + "/home";}

    @RequestMapping(value = "/{locale}/home")
    public String homeWithLocale(Model model) {
        return "home";
    }
}

