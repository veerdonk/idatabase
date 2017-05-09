package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.service.SnpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dvandeveerdonk on 9-5-17.
 */
@Controller
public class SnpController {

    @Autowired
    SnpService snpService;

    @RequestMapping(value = "/{locale}/snp")
    public String snpPage(Model model, @RequestParam("id") String id){
        model.addAttribute("snpId", id);
        snpService.getSnpByGene("test"); //TODO remove test
        return "/snpPage";
    }
}
