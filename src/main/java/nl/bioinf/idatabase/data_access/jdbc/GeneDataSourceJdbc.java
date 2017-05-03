package nl.bioinf.idatabase.data_access.jdbc;


import nl.bioinf.idatabase.data_access.GeneDataSource;
import nl.bioinf.idatabase.model.Gene;
import nl.bioinf.idatabase.model.StressFactor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dvandeveerdonk on 6-3-17.
 * Connects to the MySQL database to retrieve info
 * about genes
 */
@Component
public class GeneDataSourceJdbc implements GeneDataSource{

    private final JdbcTemplate jdbcTemplate;

    public GeneDataSourceJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Searches the database for genes with the given
     * ensembl ID, returns an instance of the Gene class
     * @param ensId
     * @return
     */
    @Override
    public List<Gene> getGeneByensId(String ensId) {
        return jdbcTemplate.query("SELECT * FROM DE_genes WHERE ens_id = ?", new GeneMapper(), ensId);
    }

    /**
     * Searches the database for Genes with given gene name
     * returns an instance of the Gene class
     * @param geneName
     * @return
     */
    @Override
    public List<Gene> getGeneByGeneName(String geneName) {
        return jdbcTemplate.query("SELECT * FROM DE_genes WHERE gene_name = ?", new GeneMapper(), geneName);
    }

    private static final class GeneMapper implements RowMapper<Gene>{

        /**
         * Implementations must implement this method to map each row of data
         * in the ResultSet. This method should not call {@code next()} on
         * the ResultSet; it is only supposed to map values of the current row.
         *
         * @param rs     the ResultSet to map (pre-initialized for the current row)
         * @param rowNum the number of the current row
         * @return the result object for the current row
         * @throws SQLException if a SQLException is encountered getting
         *                      column values (that is, there's no need to catch SQLException)
         */
        @Override
        public Gene mapRow(ResultSet rs, int rowNum) throws SQLException {
            Gene gene = new Gene(
                    rs.getInt("id"),
                    rs.getString("stress_factor"),
                    rs.getString("timepoint"),
                    rs.getString("ens_id"),
                    rs.getDouble("log2fold"),
                    rs.getDouble("pval"),
                    rs.getString("gene_name"));
            return gene;
        }
    }

    /**
     * queries the database for a gene name or ensembl id that
     * matches (part) of a string provided by the ajax autocomplete
     * controller
     * @param query
     * @return
     */
    @Override
    public List<String> getNames(String query) {
        String queryToUse = "%"+query+"%";
        List<String> names = jdbcTemplate.queryForList("SELECT DISTINCT (gene_name) FROM DE_genes WHERE gene_name LIKE ?", String.class, queryToUse);
        names.addAll(jdbcTemplate.queryForList("SELECT DISTINCT (ens_id) FROM DE_genes WHERE ens_id LIKE ?", String.class, queryToUse));
        return names;
    }

    /**
     * Counts all distinct genes per stress factor and returns these numbers
     * @return
     */
    @Override
    public List<StressFactor> numberOfGenesPerVector() {
        List<StressFactor> stressFactors = new LinkedList<>();

//        HashMap numberOfgenes = new HashMap();
        List<String> timepoints = jdbcTemplate.queryForList("SELECT DISTINCT timepoint FROM DE_genes;", String.class);
        for (String factor : jdbcTemplate.queryForList("SELECT DISTINCT stress_factor FROM DE_genes;", String.class)) {
            for (String tp : timepoints) {
//                numberOfgenes.put(factor + tp, jdbcTemplate.queryForObject("SELECT COUNT(DISTINCT gene_name) FROM DE_genes WHERE stress_factor =? AND timepoint =?", int.class, factor, tp));
                stressFactors.add(new StressFactor(factor, tp, jdbcTemplate.queryForObject("SELECT COUNT(DISTINCT gene_name) FROM DE_genes WHERE stress_factor =? AND timepoint =?", int.class, factor, tp)));
            }
        }
    return stressFactors;
    }


}
