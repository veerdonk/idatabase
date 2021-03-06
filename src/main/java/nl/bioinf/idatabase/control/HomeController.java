package nl.bioinf.idatabase.control;

import org.springframework.stereotype.Controller;
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

    /**
     * listens to possible homepage requests, adds
     * the locale to the url and resirects to home
     * @param locale
     * @return redirect to /{locale}/home
     */
    @RequestMapping(
            value = {"", "/", "home", "/home", "/home.html"})
            public String home(Locale locale){
        return "redirect:" + locale.getLanguage() + "/home";}

    /**
     * receives redirect
     * @return reference to the home.html template
     */
    @RequestMapping(value = "/{locale}/home")
    public String homeWithLocale() {return "home";}
}

