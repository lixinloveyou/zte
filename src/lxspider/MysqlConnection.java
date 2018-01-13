package lxspider;
import java.sql.*;
public class MysqlConnection {
	public static boolean selectUrlFromMysql(String str) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean flage = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/spiderurl?user=root&password="+Initial.getPropertiesdInit("password"));
			stmt = conn.createStatement();
			rs=stmt.executeQuery("select MD5number from visitedurl where MD5number = '" + str+ "'");
			if(rs.next())
			{
				flage = true;
			}
			else {
				
				flage = false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				if( rs != null) {
					rs.close();
					rs = null;
				}
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return flage;

	}
	public static void addtUrlToMysql(String str) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/spiderurl?user=root&password="+Initial.getPropertiesdInit("password"));
			stmt = conn.createStatement();/*10.0.1.6:3306*/
			stmt.execute("insert into visitedurl values('"+str+"')");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			try {

				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

}

