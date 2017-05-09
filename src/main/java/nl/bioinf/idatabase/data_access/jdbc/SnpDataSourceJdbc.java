package nl.bioinf.idatabase.data_access.jdbc;

import nl.bioinf.idatabase.data_access.SnpDataSource;
import nl.bioinf.idatabase.model.SNP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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

    /**
     * Uses getQtlTables to get a list of available tables
     * goes through these tables to find all matches of given
     * snp id returns a hashmap with tablename as key and
     * SNP array as value
     * @param snpId
     * @return snps
     */
    @Override
    public Map<String, List<SNP>> getSnpById(String snpId) {
        List<String> qtlTables = getQtlTables();
        Map<String, List<SNP>> snps = new HashMap<>();

        for(String table:qtlTables){
            String sql = "SELECT * FROM " + table + " WHERE snp=?";
            List<SNP> snpList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(SNP.class), snpId);
            snps.put(table, snpList);
        }

        return snps;
    }

    @Override
    public Map<String, List<SNP>> getSnpByGeneName(String geneName) {

        List<String> snpList = new ArrayList<>();
        snpList.add("rs12504790");
        snpList.add("rs60133380");
        Map<String, List<SNP>> allSnps = new HashMap<>();

        for(String snp : snpList){
            Map<String, List<SNP>> newSnps = getSnpById(snp);

            for(String key : newSnps.keySet()){
                if (allSnps.containsKey(key))  {
                    allSnps.get(key).addAll(newSnps.get(key));
                    System.out.println(key);
                    System.out.println(allSnps.get(key));
                }
                else{
                    allSnps.put(key, newSnps.get(key));
                    System.out.println(key);
                    System.out.println(allSnps.get(key));
                }

            }
        }
        System.out.println(allSnps);
        return null;
    }

    /**
     * Retrieves all tablenames ending in 'qtl'
     * @return List of tablenames
     */
    public List<String> getQtlTables(){
        return jdbcTemplate.queryForList("SELECT table_name FROM information_schema.tables WHERE table_name like '%qtl'", String.class);
    }
}
