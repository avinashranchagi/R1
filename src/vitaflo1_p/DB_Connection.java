package vitaflo1_p;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Connection 
{

	public static void main(String[] args) throws SQLException 
	{
		String connectionurl = "jdbc:sqlserver://localhost:1433";
		Connection db = DriverManager.getConnection(connectionurl,"sa","s@l");
		Statement query= db.createStatement(); 
		query.executeQuery("");
	}

}
