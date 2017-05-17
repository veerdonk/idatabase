package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.model.SnpEntry;
import nl.bioinf.idatabase.service.SnpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dvandeveerdonk on 9-5-17.
 * Controller for the qtl snp search pages
 * serves a page as well as database querying
 * trough the snpService
 */
@Controller
public class SnpController {

    @Autowired
    SnpService snpService;

    /**
     * Serves the snp page and ties parameters from a form
     * to the model.
     * @param model
     * @param id
     * @param region
     * @return the snp html page
     */
    @RequestMapping(value = "/{locale}/snp")
    public String snpPage(Model model, @RequestParam("qtlId") String id, @RequestParam(value = "region", required = false) Integer region){
        model.addAttribute("snpId", id);
        if(region != null){
            model.addAttribute("region", region);
        }
        return "/snpPage";
    }

    /**
     * Based on a given snp identifier (rsid or genename) this method
     * uses snpService to retrieve all snps from the database that
     * match those found in the ensembl database
     * @param id
     * @param region
     * @return a list of SnpEntry objects (contain multiple snps)
     */
    @ResponseBody
    @RequestMapping(value = "/api/getSnpFromDb")
    public List<SnpEntry> getSnps(@RequestParam("qtlId") String id, @RequestParam(value = "region", required = false) Integer region){
        List<SnpEntry> snps = new ArrayList<>();
        if(id.matches("rs\\d*")){
            snps.add(snpService.getSnps(id));
            return snps;
        }
        else{
            if(region==null){
                region=0;
            }
            System.out.println(snpService.getSnpByGene(id, region).size());
           return snpService.getSnpByGene(id, region);
        }
    }
}
