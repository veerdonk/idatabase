package nl.bioinf.idatabase;

import nl.bioinf.idatabase.control.GeneController;
import nl.bioinf.idatabase.dataAccess.GeneDataSource;
import nl.bioinf.idatabase.dataAccess.jdbc.GeneDataSourceJdbc;
import nl.bioinf.idatabase.model.Gene;
import nl.bioinf.idatabase.service.GeneService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdatabaseApplicationTests {

	/**
	 * Tests the optimal situation where a gene exists in the database
     * and is returned without fault
	 */
	@Test
	public void checkGeneFromDb() {

	}

}
