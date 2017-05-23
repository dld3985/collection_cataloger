package database_utilities;

import static database_utilities.Provider.*;

import java.sql.*;

/**
 * Singleton instance of a connection to the database.
 * @author Dan
 *
 */
public class ConnectionProvider {
	private static Connection con = null;
	static {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				 
				e1.printStackTrace();
			}
		}
	}

	public static Connection getCon() {
		return con;
	}

}