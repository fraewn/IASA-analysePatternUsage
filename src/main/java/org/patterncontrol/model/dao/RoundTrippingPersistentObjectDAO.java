package org.patterncontrol.model.dao;

import org.patterncontrol.model.dto.RoundTrippingPersistentObjectDTO;
import org.patterncontrol.model.util.ProjectDatabaseCredentials;
import org.patterncontrol.service.neo4jConnect;

public class RoundTrippingPersistentObjectDAO {
    private static RoundTrippingPersistentObjectDAO instance=null;
    private RoundTrippingPersistentObjectDAO(){}
    public static RoundTrippingPersistentObjectDAO getInstance(){
        if(instance==null) {
            instance = new RoundTrippingPersistentObjectDAO();
        }
        return instance;
    }

    // method to execute queries to check strategy pattern implementation
    public RoundTrippingPersistentObjectDTO checkRoundTrippingPersistentObjectImplementation() throws Exception {
        String url = ProjectDatabaseCredentials.getProjectdatabase_url();
        String user = ProjectDatabaseCredentials.getProjectdatabase_username();
        String password = ProjectDatabaseCredentials.getProjectdatabase_password();

        // class to get user data from database
        try ( neo4jConnect connection = new neo4jConnect(url, user, password))
        {
            // send request with username and password to neo4j
            // connection return userdto_db with username if successful
            return connection.checkRoundTrippingPersistentObjectImplementation();
        }
    }
}
