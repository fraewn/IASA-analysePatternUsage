package org.patterncontrol.model.dao;

import org.patterncontrol.model.dto.CommandPatternDTO;
import org.patterncontrol.service.neo4jConnect;

public class CommandPatternDAO {
	private static CommandPatternDAO instance=null;
	private CommandPatternDAO(){}
	public static CommandPatternDAO getInstance(){
		if(instance==null) {
			instance = new CommandPatternDAO();
		}
		return instance;
	}

	public CommandPatternDTO checkCommandImplementation(String projectdatabase_url, String projectdatabase_user, String projectdatabase_password) throws Exception {
		// class to get user data from database
		try ( neo4jConnect connection = new neo4jConnect(projectdatabase_url, projectdatabase_user, projectdatabase_password))
		{
			// send request with username and password to neo4j
			// connection return userdto_db with username if successful
			return connection.checkCommandImplementation();
		}
	}
}
