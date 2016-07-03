package dbunit;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DbUnitExport {

	private static final String ARTICLE = "article";
	private static final String CATEGORY = "category";
	private static final String EMPLOYEE = "employee";
	private static final String CUSOMER = "customer";
	private static final String OFFER = "offer";
	private static final String OFFER_POSITION = "offerPosition";

	public static void main(String[] args) throws Exception {
		// database connection
		Class driverClass = Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bpj_test", "bpj", "bpj");
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		// partial database export
		QueryDataSet partialDataSet = new QueryDataSet(connection);
		partialDataSet.addTable(CATEGORY);
		partialDataSet.addTable(ARTICLE);
		partialDataSet.addTable(EMPLOYEE);
		partialDataSet.addTable(CUSOMER);
		partialDataSet.addTable(OFFER);
		partialDataSet.addTable(OFFER_POSITION);

		FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

	}
}
