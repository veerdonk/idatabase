package nl.bioinf.idatabase.model;

import java.util.List;

/**
 * Created by dvandeveerdonk on 29-3-17.
 * Object to contain summarizing information
 * about a database
 */
public class DbSummary {
    private int TotalGenes;
    private int uniqueGenes;
    private List<stressFactor> stressFactors;
    private List<Gene> top10Genes;
    private List<Gene> low10Genes;
}
