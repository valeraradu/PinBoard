package org.pinboard.util;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Startup;



@Startup
@DataSourceDefinition(
		name = "java:global/jdbc/PinBoardDS",
		serverName = "localhost",
		portNumber = 3306,
        className="com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
        url = "jdbc:mysql://localhost:3306",
        user = "root",
        password = "root",
        databaseName = "PinBoardDB",
        properties = {"createDatabaseIfNotExist=true"}
)
public class DBInit {

}
