package nl.bioinf.idatabase.service;

import nl.bioinf.idatabase.model.Gene;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dvandeveerdonk on 6-4-17.
 * Tests the geneService class and its methods
 * the gene service manages retrieving data from
 * the database and as such is an integral part of
 * th application
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneServiceTest {

@Autowired
GeneService geneService;

    /**
     * Sunny day test where the gene 'SKAP2' is
     * retrieved from the database and all its
     * values are checked
     */
    @Test
    public void getGeneTest(){
        List<Gene> expectedGenes = new ArrayList<>(Arrays.asList(
                new Gene(2,
                        "Aspergillus_fumigatus",
                        "4h",
                        "ENSG00000005020",
                        -1.39894513242005,
                        1.72371377570332E-28,
                        "SKAP2")));

        List<Gene> genes = geneService.getGene("SKAP2");
        assertEquals(expectedGenes, genes);
    }

    /**
     * Test where the the method getGene is passed
     * null as argument. The expectation is that the
     * method throws a NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void nullInputTest(){
        geneService.getGene(null);
    }

    /**
     * Test where the databse is queried for a
     * non existant gene. Expected is that a
     * list with no genes is returned.
     */
    @Test
    public void noGeneFoundTest(){
        List<Gene> expectedGenes = new ArrayList<>();
        List<Gene> genes = geneService.getGene("ThisIsNotAnExistingGene");

        assertEquals(expectedGenes, genes);
    }

    /**
     * Sunny day scenario for the function that retrieves
     * information from the database to be used for autocomplete
     * A list of gene names is expected
     */
    @Test
    public void getNamesTest(){
        List<String> expectedGenes = new ArrayList<>(Arrays.asList("SKAP2"));
        List<String> genes = geneService.getNames("KAP2");

        assertEquals(expectedGenes, genes);
    }

    /**
     * Test for the getNames method where a null input is tested
     * expected is a nullpointerexception
     */
    @Test(expected = NullPointerException.class)
    public void nullInputGetNamesTest(){
        geneService.getNames(null);
    }
}