package skeleton;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;

/**
 * Handles loading and destroying of skull fixtures
 */
public abstract class SkullDBFixture extends DBFixture {

	/** Fixtures. */
	private static final String FIXTURES_FILE = "src/test/resources/fixtures/skull.xml";

	/**
	 * Load skull fixtures.
	 * 
	 * @throws DatabaseUnitException
	 *             database unit exception
	 * @throws SQLException
	 *             SQL exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Before
	public void loadSkulls() throws DatabaseUnitException, SQLException,
			IOException {
		loadFixturesFile(FIXTURES_FILE);
	}

	/**
	 * Removes the skull fixtures.
	 * 
	 * @throws SQLException
	 *             SQL exception
	 * @throws DatabaseUnitException
	 *             database unit exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@After
	public void removeSkulls() throws SQLException, DatabaseUnitException,
			IOException {
		removeFixturesFile(FIXTURES_FILE);
	}

}