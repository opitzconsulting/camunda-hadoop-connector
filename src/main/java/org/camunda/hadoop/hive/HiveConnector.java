package org.camunda.hadoop.hive;

import static org.camunda.hadoop.HadoopBPMNErrorFactory.throwToManyResults;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HiveConnector
{

  public List<String[]> query(HiveJobConfig config) throws SQLException, ClassNotFoundException
  {
    Connection con = connect(config);
    Statement stmt = con.createStatement();
    try
    {
      if (config.getQuery() != null && !config.getIsUpdate())
      {
        ResultSet resultSet = stmt.executeQuery(config.getQuery());
        if (config.isReturningResult())
        {
          return getResult(resultSet, config.getMaxResults());
        }
      }
      else
      {
        stmt.executeUpdate(config.getQuery());
      }
    } finally
    {
      if (stmt != null)
      {
        stmt.close();
      }
      if (con != null)
      {
        con.close();
      }
    }
    return null;
  }

  private Connection connect(HiveJobConfig config) throws ClassNotFoundException, SQLException
  {
    String driverName = "org.apache.hive.jdbc.HiveDriver";
    String connectionName = "jdbc:hive2://" + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase();
    Class.forName(driverName);
    return config.getUser() != null && config.getPassword() != null
        ? DriverManager.getConnection(connectionName, config.getUser(), config.getPassword())
        : DriverManager.getConnection(connectionName);
  }

  private List<String[]> getResult(ResultSet resultSet, int maxResults) throws SQLException
  {
    List<String[]> resultList = new ArrayList<String[]>();
    ResultSetMetaData metaData = resultSet.getMetaData();
    while (resultSet.next())
    {
      String[] line = new String[metaData.getColumnCount()];
      for (int i = 0; i < line.length; i++)
      {
        line[i] = resultSet.getString(i + 1);
      }
      resultList.add(line);
      if (resultList.size() == maxResults)
      {
        throwToManyResults();
      }
    }
    return resultList;
  }
}
