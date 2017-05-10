package nl.bioinf.idatabase.data_access.jdbc;

import nl.bioinf.idatabase.data_access.SnpDataSource;
import nl.bioinf.idatabase.model.SNP;
import nl.bioinf.idatabase.model.rest.EnsemblSNP;
import nl.bioinf.idatabase.model.rest.GeneMetaData;
import nl.bioinf.idatabase.model.rest.SnpRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * Created by dvandeveerdonk on 8-5-17.
 * Connects to a MySQL database to retrieve snps
 * or uses the snp controller to retrieve snps from
 * ensembl and then retrieve those from the database
 */
@Component
public class SnpDataSourceJdbc implements SnpDataSource {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SnpRestController snpRestController;

    /**
     * Queries the database for a single SNP.
     * query is based on a given snp id and returns
     * a list of SNP objects
     * @param snpId
     * @return List of SNP objects
     */
    @Override
    public List<SNP> getSnpById(String snpId) {
        String sql = "SELECT * FROM qtl WHERE snp=?";
        List<SNP> snpList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(SNP.class), snpId);
        return snpList;
    }

    /**
     * Method to retrieve all snps found on and around a gene from the database
     * this method uses the SnpRestController to connect to the ensembl
     * rest api and retrieves all snps around that gene.
     * These snps are then queried against the qtl database to find
     * any matches. These are then returned as a list of SNP objects
     * @param geneName
     * @return List of SNP objects
     */
    @Override
    public List<SNP> getSnpByGeneName(String geneName) {
        Instant start = Instant.now();
        List<SNP> allSnps = new ArrayList<>();

        GeneMetaData gmd = snpRestController.snps("BAALC", 250000);

        for(EnsemblSNP ensemblSNP : gmd.getSnps()){
            String snp = ensemblSNP.getId();
            allSnps.addAll(getSnpById(snp));
            }
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
        return allSnps;
        }
}
