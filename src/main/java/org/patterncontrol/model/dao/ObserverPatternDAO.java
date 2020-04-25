package org.patterncontrol.model.dao;

import org.patterncontrol.model.dto.ObserverPatternDTO;
import org.patterncontrol.model.util.ProjectDatabaseCredentials;
import org.patterncontrol.service.neo4jConnect;

public class ObserverPatternDAO {
    private static ObserverPatternDAO instance=null;
    private ObserverPatternDAO(){}
    public static ObserverPatternDAO getInstance(){
        if(instance==null) {
            instance = new ObserverPatternDAO();
        }
        return instance;
    }

    // method to execute queries to check strategy pattern implementation
    public ObserverPatternDTO checkObserverImplementation() throws Exception {
        String url = ProjectDatabaseCredentials.getProjectdatabase_url();
        String user = ProjectDatabaseCredentials.getProjectdatabase_username();
        String password = ProjectDatabaseCredentials.getProjectdatabase_password();

        // class to get user data from database
        try ( neo4jConnect connection = new neo4jConnect( url, user, password))
        {
            // send request with username and password to neo4j
            // connection return userdto_db with username if successful
            return connection.checkObserverImplementation();
        }
    }
}
