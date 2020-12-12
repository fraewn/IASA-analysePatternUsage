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

			int project_id = Integer.parseInt(delegateExecution.getVariable( "project_id").toString());
			String project_giturl = "";
			String projectdatabase_url = "";
			String projectdatabase_user = "";
			String projectdatabase_password = "";

			ResultSet rs_giturl = getCreds(connection, "SELECT PROJECT_GITURL FROM PROJECT WHERE PROJECT_ID=?", project_id);
			while(rs_giturl.next()) {
				project_giturl = rs_giturl.getString(1);
			}
			ResultSet rs_databaseCreds = getCreds(connection, "SELECT PROJECTDATABASE_URL, PROJECTDATABASE_USER, PROJECTDATABASE_PASSWORD FROM " +
					"PROJECTDATABASE WHERE PROJECT_ID=?", project_id);
			while(rs_databaseCreds.next()){
				projectdatabase_url = rs_databaseCreds.getString(1);
				projectdatabase_user = rs_databaseCreds.getString(2);
				projectdatabase_password = rs_databaseCreds.getString(3);
			}

			String test = project_id + ": giturl: " + project_giturl + ", project_databaseurl: " + projectdatabase_url  +
					", projectdatabase_user: " + projectdatabase_user + ", projectdatabasepassword: " + projectdatabase_password;
			System.out.println(test);
			LoggerDelegate loggerDelegate = new LoggerDelegate();
			loggerDelegate.printLoggingInfo(test);

			delegateExecution.setVariable("project_giturl", project_giturl);
			delegateExecution.setVariable("projectdatabase_url", projectdatabase_url);
			delegateExecution.setVariable("projectdatabase_user", projectdatabase_user);
			delegateExecution.setVariable("projectdatabase_password", projectdatabase_password);


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
