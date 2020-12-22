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
	public StrategyPatternDTO checkStrategyImplementation(String projectdatabase_url, String projectdatabase_user, String projectdatabase_password) throws Exception {
		// class to get user data from database
		try ( neo4jConnect connection = new neo4jConnect(projectdatabase_url, projectdatabase_user, projectdatabase_password))
		{
			// send request with username and password to neo4j
			return connection.checkStrategyImplementation();
		}
	}
}
