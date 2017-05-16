package nl.bioinf.idatabase.control;

import nl.bioinf.idatabase.model.rest.EnsemblId;
import nl.bioinf.idatabase.model.rest.EnsemblSNP;
import nl.bioinf.idatabase.model.rest.GeneMetaData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by dvandeveerdonk on 8-5-17.
 * REST controller used for finding a variety of information
 * about a human gene or genomic region
 *
 * provides endpoints of every step of the search process as
 * well as a 'total' mode
 *
 * This is all done using the ensembl REST api
 */
@RestController
public class SnpRestController {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${ensembl.url}") //first part of the ensembl url, this is reused
    private String ensemblUrl;

    /**
     * using the ensembl REST api all SNPs in the area of a given gene are
     * returned in a list.
     * @param gene
     * @return genemetadata object containing list of snps
     */
    @RequestMapping(value = "/api/SNPbyGeneName")
    public GeneMetaData snps(@RequestParam(value = "gene") String gene, @RequestParam(value = "region", required = false) Integer region){


        if(!gene.matches("ENSG\\d*")){
            EnsemblId ensId = ensemblId(gene);
            gene = ensId.getId();
        }
        GeneMetaData ensemblGene = ensemblGene(gene);
        EnsemblSNP[] snps = getSnps(ensemblGene.getSeq_region_name(), ensemblGene.getStart(), ensemblGene.getEnd(), region);

        ensemblGene.setNumberOfSnps(snps.length);
        ensemblGene.setSnps(Arrays.asList(snps));

        return ensemblGene;
    }

    /**
     * Retrieves information about a given gene from the ensembl database
     * this is stored in a gene meta data object
     * @param id
     * @return Genemetadata object
     */
    @RequestMapping(value = "/api/getGene")
    public GeneMetaData ensemblGene(@RequestParam("id") String id) {
        String geneUrl = ensemblUrl+"lookup/id/{ensId}?content-type=application/json;expand=0";
        GeneMetaData ensemblGene = restTemplate.getForObject(geneUrl, GeneMetaData.class, id);
        return ensemblGene;
    }

    /**
     * Retrieves the ensembl stable id from a gene given a gene name
     * @param geneName
     * @return EnsemblId object
     */
    @RequestMapping(value = "/api/ensid")
    public EnsemblId ensemblId(@RequestParam("geneName") String geneName){
        String idUrl = ensemblUrl+"xrefs/symbol/homo_sapiens/{geneName}?content-type=application/json";
        EnsemblId[] ensemblId = restTemplate.getForObject(idUrl, EnsemblId[].class, geneName);
        EnsemblId ensId = Arrays.asList(ensemblId).get(0);
        return ensId;
    }

    /***
     * Given a chromosome number, a start and stop position this method retrieves
     * all snps between start and stop.
     * The optional region parameter is used to select a number of bases above
     * and below the start and stop positions of a gene.
     * @param chromosome
     * @param start
     * @param stop
     * @param region - optional, default=0
     * @return an array of EnsemblSNP objects
     */
    @RequestMapping(value = "/api/getSnps")
    public EnsemblSNP[] getSnps(@RequestParam("chromosome") int chromosome,
                                @RequestParam("start") int start,
                                @RequestParam("stop") int stop,
                                @RequestParam(value = "region", required = false) Integer region){

        String snpUrl = ensemblUrl+"overlap/region/human/{chromosome}:{start}-{stop}?feature=variation;content-type=application/json";

        if(region == null) {
            region = 0;
        }
        HashMap<String, Integer> snpParameters = new HashMap();
        snpParameters.put("chromosome", chromosome);
        snpParameters.put("start", start-region);
        snpParameters.put("stop", stop+region);

        return restTemplate.getForObject(snpUrl, EnsemblSNP[].class, snpParameters);
    }
}
