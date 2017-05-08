package nl.bioinf.idatabase.data_access.jdbc;

import nl.bioinf.idatabase.data_access.SnpDataSource;
import nl.bioinf.idatabase.model.SNP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dvandeveerdonk on 8-5-17.
 */
@Component
public class SnpDataSourceJdbc implements SnpDataSource {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Map getSnpById(String snpId) {
        List<String> qtlTables = getQtlTables();
        Map<String, List<SNP>> snps = new HashMap<>();

        for(String table:qtlTables){
            String sql = "SELECT * FROM " + table + " WHERE snp=?";
            List<SNP> snpList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(SNP.class), snpId);
            snps.put(table, snpList);
        }
        System.out.println(snps);
        return snps;
    }

    @Override
    public List<SNP> getSnpByGeneName(String geneName) {
        return null;
    }

    public List<String> getQtlTables(){
        return jdbcTemplate.queryForList("SELECT table_name FROM information_schema.tables WHERE table_name like '%qtl'", String.class);
    }
}
