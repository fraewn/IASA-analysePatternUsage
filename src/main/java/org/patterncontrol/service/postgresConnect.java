package org.patterncontrol.service;
import java.sql.Connection;
import java.sql.DriverManager;

public class postgresConnect {
	public Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/iasa_global",
							"iasa", "abc");
			System.out.println("Opened database successfully");

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table created successfully");
		return connection;
	}
}
