package org.balanpy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
	
	    //Indicamos donde está la bd, en este caso en localhost/puerto/nombre de la bd que creamos en workbench. 
		private static final String JDBC_URL = "jdbc:mysql://localhost:3306/database_balanpy";
		private static Connection instance = null;
		
		private ConexionBD() {
			
		}
		
		public static Connection getConnection() throws SQLException {
			if (instance == null) {
				//Cadenas de valores
				Properties props = new Properties();
				//Estructura de clave:valor
				props.put("user", "root");
				props.put("password", "");
				//Establecemos la conexión a través de estos parámetros(ruta db, nombre usuario, contrasenia) para acceder a la db.
				instance = DriverManager.getConnection(JDBC_URL, props);
				System.out.println("Successful connection to the database");
			}
			return instance;
		}

}
