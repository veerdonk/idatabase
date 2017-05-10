package nl.bioinf.idatabase.model.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by dvandeveerdonk on 8-5-17.
 * REST controller used for finding all SNPs associated with a single human gene
 * converts a gene name to ensembl id and uses that to find start and stop positions
 * these positions +/- 250 kB are used to search for SNPs in that area.
 *
 * This is all done using the ensembl REST api
 */
@RestController
public class SnpRestController {
    private RestTemplate restTemplate = new RestTemplate();
    /**
     * using the ensembl REST api all SNPs in the area of a given gene are
     * returned in a list.
     * @param gene
     * @return Array of EnsemblSNP objects
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

    @RequestMapping(value = "/api/getGene")
    public GeneMetaData ensemblGene(@RequestParam("id") String id) {
        String geneUrl = "http://rest.ensembl.org/lookup/id/{ensId}?content-type=application/json;expand=0";
        GeneMetaData ensemblGene = restTemplate.getForObject(geneUrl, GeneMetaData.class, id);
        return ensemblGene;
    }

    @RequestMapping(value = "/api/ensid")
    public EnsemblId ensemblId(@RequestParam("geneName") String geneName){
        String idUrl = "http://rest.ensembl.org/xrefs/symbol/homo_sapiens/{geneName}?content-type=application/json";
        EnsemblId[] ensemblId = restTemplate.getForObject(idUrl, EnsemblId[].class, geneName);
        EnsemblId ensId = Arrays.asList(ensemblId).get(0);
        return ensId;
    }

    @RequestMapping(value = "/api/getSnps")
    public EnsemblSNP[] getSnps(@RequestParam("chromosome") int chromosome,
                                @RequestParam("start") int start,
                                @RequestParam("stop") int stop,
                                @RequestParam(value = "region", required = false) Integer region){

        String snpUrl = "http://rest.ensembl.org/overlap/region/human/{chromosome}:{start}-{stop}?feature=variation;content-type=application/json";

        if(region == null) {
            region = 0;
        }
        HashMap<String, Integer> snpParameters = new HashMap();
        snpParameters.put("chromosome", chromosome);
        snpParameters.put("start", start-region);
        snpParameters.put("stop", stop+region);

        EnsemblSNP[] snps = restTemplate.getForObject(snpUrl, EnsemblSNP[].class, snpParameters);
        return snps;
    }
}
