package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.model.HeatmapData;
import nl.bioinf.idatabase.model.SNP;
import nl.bioinf.idatabase.model.SnpEntry;
import nl.bioinf.idatabase.service.SnpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.*;

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
     * @return a list of SnpEntry objects (contains multiple snps)
     */
    @ResponseBody
    @RequestMapping(value = "/api/getSnpFromDb")
    public HeatmapData getSnps(@RequestParam("qtlId") String id, @RequestParam(value = "region", required = false) Integer region){
        List<SnpEntry> snps = new ArrayList<>();
        if(id.matches("rs\\d*")){
            snps.add(snpService.getSnps(id));
            return heatmap(snps, "cytokine");
        }
        else{
            if(region==null){
                region=0;
            }
            System.out.println(snpService.getSnpByGene(id, region).size());
           return heatmap(snpService.getSnpByGene(id, region), "cytokine");
        }
    }

    public HeatmapData heatmap(List<SnpEntry> snps, String qtlType){
        HeatmapData hmp = new HeatmapData();
        ArrayList<String> columns = new ArrayList<>();
        for(SnpEntry snpEntry:snps){
            for(SNP snp : snpEntry.getSnps()){
                if(Objects.equals(snp.getQtl_type(), qtlType)){
                    if(!columns.contains(snp.getCell_type())){
                        columns.add(snp.getCell_type());
                    }

                }
            }
        }
        hmp.setX(columns);
        int arrLen = columns.size();
        System.out.println(arrLen);

        ArrayList<String> rows = new ArrayList<>();
        ArrayList<Double[]> values = new ArrayList<>();
        for(SnpEntry snpEntry:snps){
            Double[] curSnpValues = new Double[arrLen];
            for(SNP snp:snpEntry.getSnps()){
                if(Objects.equals(snp.getQtl_type(), qtlType)){
                    assert curSnpValues != null;
                    int i = columns.indexOf(snp.getCell_type());
                    curSnpValues[i] = snp.getPval();// hier gaat het nog goed
                }
                else{
                    curSnpValues = null;
                }
            }
            if(curSnpValues != null){
                rows.add(snpEntry.getId());
                Collections.addAll(values, curSnpValues);//Add verplaatst alle null waarden naar eind van array = niet goed.... verdorie
//                values.add(curSnpValues);
            }

        }
        for(Double[] val:values){
            System.out.println(Arrays.toString(val));
        }
        hmp.setZ(values);
        hmp.setY(rows);
        hmp.setType("heatmap");

        return hmp;
    }
}
