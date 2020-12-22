package org.patterncontrol.model.dao;

import org.patterncontrol.model.dto.ProxyPatternDTO;
import org.patterncontrol.model.util.ProjectDatabaseCredentials;
import org.patterncontrol.service.neo4jConnect;

public class ProxyPatternDAO {
    private static ProxyPatternDAO instance=null;
    private ProxyPatternDAO(){}
    public static ProxyPatternDAO getInstance(){
        if(instance==null) {
            instance = new ProxyPatternDAO();
        }
        return instance;
    }

    // method to execute queries to check strategy pattern implementation
    public ProxyPatternDTO checkProxyImplementation(String projectdatabase_url, String projectdatabase_user, String projectdatabase_password) throws Exception {
        // class to get user data from database
        try ( neo4jConnect connection = new neo4jConnect(projectdatabase_url, projectdatabase_user, projectdatabase_password))
        {
            // send request with username and password to neo4j
            // connection return userdto_db with username if successful
            return connection.checkProxyImplementation();
        }
    }
}
