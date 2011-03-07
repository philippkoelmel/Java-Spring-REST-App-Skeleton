package skeleton;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Handles the loading of fixtures.
 */
public abstract class DBFixture {

	/** The data source. */
	@Autowired
	DataSource dataSource;

	/**
	 * Removes the fixtures.
	 * 
	 * @param fileName
	 *            the file name
	 * @throws SQLException
	 *             SQL exception
	 * @throws DatabaseUnitException
	 *             database unit exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void removeFixturesFile(String fileName) throws SQLException,
			DatabaseUnitException, IOException {
		DatabaseOperation.DELETE_ALL.execute(getConnection(),
				getDataset(fileName));
	}

	/**
	 * Load fixtures.
	 * 
	 * @param fileName
	 *            the file name
	 * @throws DatabaseUnitException
	 *             the database unit exception
	 * @throws SQLException
	 *             SQL exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void loadFixturesFile(String fileName)
			throws DatabaseUnitException, SQLException, IOException {
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(),
				getDataset(fileName));
	}

	/**
	 * Gets the dataset by reading an xml file.
	 * 
	 * @param fileName
	 *            the file name
	 * @return the dataset
	 * @throws DataSetException
	 *             the data set exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private IDataSet getDataset(String fileName) throws DataSetException,
			IOException {
		File file = new File(fileName);
		return new FlatXmlDataSet(file);
	}

	/**
	 * Gets the connection from the datasource.
	 * 
	 * @return the connection
	 * @throws SQLException
	 *             SQL exception
	 * @throws DatabaseUnitException
	 *             database unit exception
	 */
	private IDatabaseConnection getConnection() throws SQLException,
			DatabaseUnitException {
		Connection con = dataSource.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(con);
		return connection;
	}

}