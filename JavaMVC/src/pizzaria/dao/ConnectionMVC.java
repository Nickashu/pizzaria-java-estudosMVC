package pizzaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pizzaria.Config;

public class ConnectionMVC {
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException erro) {
			erro.printStackTrace();
		}
		
		try {
			Config config = new Config();
			/*
			 * path -> DB path with name and port
			 * us -> DB user
			 * pass -> DB connection password
			 */
			conn = DriverManager.getConnection(config.path, config.us, config.pass);
		}
		catch(SQLException erro) {
			erro.printStackTrace();
		}
		
		return conn;
	}

}
