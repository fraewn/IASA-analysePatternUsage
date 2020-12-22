package org.patterncontrol.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.patterncontrol.service.postgresConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class setDelegateGetProjectCreds implements JavaDelegate {
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		try{
			postgresConnect postgresConnect = new postgresConnect();
			Connection connection = postgresConnect.getConnection();

			// get project_id that is inserted as a variable via REST-call that starts the process
			// project_id is the id of the project to be analysed
			int project_id = Integer.parseInt(delegateExecution.getVariable( "project_id").toString());

			// variables for database creds of neo4j project database
			String projectdatabase_url = "";
			String projectdatabase_user = "";
			String projectdatabase_password = "";

			// for this specific project, the global_database is asked for the neo4j project database creds
			ResultSet rs_databaseCreds = getCreds(connection, "SELECT PROJECTDATABASE_URL, PROJECTDATABASE_USER, PROJECTDATABASE_PASSWORD FROM " +
					"PROJECTDATABASE WHERE PROJECT_ID=?", project_id);
			while(rs_databaseCreds.next()){
				projectdatabase_url = rs_databaseCreds.getString(1);
				projectdatabase_user = rs_databaseCreds.getString(2);
				projectdatabase_password = rs_databaseCreds.getString(3);
			}

			// some tests and logging
			String test = project_id + ", project_databaseurl: " + projectdatabase_url  +
					", projectdatabase_user: " + projectdatabase_user + ", projectdatabasepassword: " + projectdatabase_password;
			System.out.println(test);
			LoggerDelegate loggerDelegate = new LoggerDelegate();
			loggerDelegate.printLoggingInfo(test);

			// neo4j project database creds are set as global variables in camunda bpmn process
			delegateExecution.setVariable("projectdatabase_url", projectdatabase_url);
			delegateExecution.setVariable("projectdatabase_user", projectdatabase_user);
			delegateExecution.setVariable("projectdatabase_password", projectdatabase_password);

			// close connection to global database iasa_global
			connection.close();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			LoggerDelegate logger = new LoggerDelegate();
			logger.execute(delegateExecution);
			logger.printLoggingInfo("Exception was thrown in setDelegateGetProjectCreds: " + e.getMessage());
		}
	}

	private ResultSet getCreds(Connection connection, String sql, int project_id) throws Exception{
		PreparedStatement stmt = null;
		stmt = connection.prepareStatement(sql);
		stmt.setInt(1, project_id);
		ResultSet rs = stmt.executeQuery();
		stmt.close();
		return rs;
	}


}
