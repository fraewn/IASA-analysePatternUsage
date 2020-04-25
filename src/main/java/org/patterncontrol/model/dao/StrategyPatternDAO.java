package org.patterncontrol.model.dao;

import org.patterncontrol.model.dto.StrategyPatternDTO;
import org.patterncontrol.model.util.ProjectDatabaseCredentials;
import org.patterncontrol.service.neo4jConnect;

public class StrategyPatternDAO {
	private static StrategyPatternDAO instance=null;
	private StrategyPatternDAO(){}
	public static StrategyPatternDAO getInstance(){
		if(instance==null) {
			instance = new StrategyPatternDAO();
		}
		return instance;
	}

	// method to execute queries to check strategy pattern implementation
	public StrategyPatternDTO checkStrategyImplementation() throws Exception {
		String url = ProjectDatabaseCredentials.getProjectdatabase_url();
		String user = ProjectDatabaseCredentials.getProjectdatabase_username();
		String password = ProjectDatabaseCredentials.getProjectdatabase_password();

		// class to get user data from database
		try ( neo4jConnect connection = new neo4jConnect(url, user, password))
		{
			// send request with username and password to neo4j
			return connection.checkStrategyImplementation();
		}
	}
}
