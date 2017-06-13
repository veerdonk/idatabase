package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.model.HeatmapData;
import nl.bioinf.idatabase.model.SNP;
import nl.bioinf.idatabase.model.SnpEntry;
import nl.bioinf.idatabase.model.dataTablesData;
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
        return "snpPage";
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
    @RequestMapping(value = "/api/getHeatmapSnpFromDb")
    public List<HeatmapData> getHeatmapSnps(@RequestParam("qtlId") String id, @RequestParam(value = "qtl") String qtl, @RequestParam(value = "region", required = false) Integer region){
        List<SnpEntry> snps = new ArrayList<>();
        if(id.matches("rs\\d*")){
            snps.add(snpService.getSnps(id));
            return heatmap(snps, qtl);
        }
        else{
            if(region==null){
                region=0;
            }
           return heatmap(snpService.getSnpByGene(id, region), qtl);
        }
    }

    /**
     * Method used by datatables to retrieve data via Ajax. connects to the database
     * to retrieve SNPs and puts them into a datatables compliant object.
     * @param id
     * @param region
     * @return dataTablesData object filled with datatables compatible data
     */
    @ResponseBody
    @RequestMapping(value = "/api/tableData")
    public dataTablesData dataTables(@RequestParam("qtlId") String id, @RequestParam(value = "region", required = false) Integer region){
        dataTablesData dataTablesData = new dataTablesData();
        ArrayList<SNP> snps = new ArrayList<>();

        if(id.matches("rs\\d*(.*)")){//is it a snp id?
            if(id.matches("(.*),(.*)")){//check for multiple
                for(String snp : id.split(",")){
                    snps.addAll(snpService.getSnps(snp).getSnps());
                }
                dataTablesData.setData(snps);
            }
            else{
                dataTablesData.setData(snpService.getSnps(id).getSnps());
            }
            return dataTablesData;
        }
        else{
            if(region==null){
                region=0;
            }
            for(SnpEntry snpEntry :snpService.getSnpByGene(id, region)){
                snps.addAll(snpEntry.getSnps());
            }
            dataTablesData.setData(snps);
            return dataTablesData;
        }
    }

    /**
     * Method to convert data from the database to a format accepted
     * by Plotly. Takes a List of SnpEntries and their qtl type then
     * iterativly builds the lists of rows, columns and values.
     * These are stored in sets of 30 to provide pagination
     * @param snps
     * @param qtlType
     * @return ArrayList of heatmaps (parts of 1 heatmap)
     */
    public List<HeatmapData> heatmap(List<SnpEntry> snps, String qtlType){
        HeatmapData hmp = new HeatmapData();

        // builing the list of columns
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
        Collections.sort(columns);
        hmp.setX(columns);
        int arrLen = columns.size(); // column size is used to set Double array size

        ArrayList<String> rows = new ArrayList<>();
        List<Double[]> values = new ArrayList<>();

        for(SnpEntry snpEntry : snps){
            Double[] curSnpValues = new Double[arrLen]; // array size is set here
            for(SNP snp : snpEntry.getSnps()){
                if(Objects.equals(snp.getQtl_type(), qtlType)){
                    if(!rows.contains(snpEntry.getId())){
                        rows.add(snpEntry.getId());
                    }
                    //find place to put pvalue in Double array based on columns
                    curSnpValues[columns.indexOf(snp.getCell_type())] = -Math.log10(snp.getPval()); //taking -log10 of pvalue
                }
            }
            if(!Arrays.equals(curSnpValues, new Double[arrLen])){
                values.add(curSnpValues);
            }

        }

        //Set the colorscale used in heatmapping
        ArrayList<ArrayList<String>> colorScale = new ArrayList<>();
        colorScale.add(new ArrayList<>(Arrays.asList("0.0", "rgb(255,255,0)")));//yellow - low
        colorScale.add(new ArrayList<>(Arrays.asList("0.5", "rgb(255,140,0)")));// orange
        colorScale.add(new ArrayList<>(Arrays.asList("0.75", "rgb(255,127,80)")));// orange
        colorScale.add(new ArrayList<>(Arrays.asList("1.0", "rgb(255,0,0)")));//red - high

        //Create pagination of heatmap
        ArrayList<HeatmapData> heatmapParts = new ArrayList<>();
            int j;
            int stop = 30;
            for (j = 0; j <= rows.size(); j += 30) {
                if(j > rows.size()-30){
                    stop = rows.size();
                }
                HeatmapData hmd = new HeatmapData();
                hmd.setZ(values.subList(j, stop)); //setting values
                hmd.setY(rows.subList(j, stop)); // setting y axis
                stop += 30;
                hmd.setX(columns); // setting x axis
                hmd.setType("heatmap");
                hmd.setQtl(qtlType);
                hmd.setColorscale(colorScale);
                heatmapParts.add(hmd);
            }
        return heatmapParts;
    }
}
