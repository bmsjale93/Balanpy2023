package org.balanpy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConexionBD {
	
	    //Indicamos donde está la bd, en este caso en localhost/puerto/nombre de la bd que creamos en workbench. 
		private static final String JDBC_URL = "jdbc:mysql://localhost:3306/database_balanpy";
		//Establecer pool de conexiones para reutilizar las conexiones existentes 
		private static BasicDataSource ds = null;
		
		private ConexionBD() {
			
		}
		
		public static Connection getConnection() throws SQLException {
			
				if (ds == null) {
					
					String user = "root";
					String password = "";
					
					ds = new BasicDataSource();
					ds.setUrl(JDBC_URL);
		            ds.setUsername(user);
		            ds.setPassword(password);
		            
					System.out.println("Successful connection to the database");
				}
				return ds.getConnection();//Primera creación del pool.
		}
}
