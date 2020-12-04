package org.patterncontrol.testDatabase;

import org.patterncontrol.camunda.LoggerDelegate;
import org.patterncontrol.service.postgresConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class testPostgresAccess {
	public static void main(String args[]){
		postgresTestConnection postgresTestConnection = new postgresTestConnection();
		postgresTestConnection.execute();
	}


}
