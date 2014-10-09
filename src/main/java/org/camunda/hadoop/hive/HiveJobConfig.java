package org.camunda.hadoop.hive;

public class HiveJobConfig
{

  public enum StoreOption
  {
    NONE, LOCAL, PROCESS
  }

  private String query = null;
  private boolean isUpdate = false;
  private String host = "127.0.0.1";
  private String port = "10000";
  private String database = "default";
  private String user = null;
  private String password = null;
  private StoreOption storeOption = StoreOption.NONE;
  private String storeVariable = null;
  private int maxResults = 100;

  public boolean isValid()
  {
    return query != null;
  }

  public boolean getIsUpdate()
  {
    return isUpdate;
  }

  public HiveJobConfig setIsUpdate(String u)
  {
    isUpdate = Boolean.valueOf(u);
    return this;
  }

  public String getQuery()
  {
    return query;
  }

  public HiveJobConfig setQuery(String query)
  {
    this.query = query;
    return this;
  }

  public String getHost()
  {
    return host;
  }

  public HiveJobConfig setHost(String host)
  {
    this.host = host;
    return this;
  }

  public String getPort()
  {
    return port;
  }

  public HiveJobConfig setPort(int port)
  {
    return setPort(String.valueOf(port));
  }

  public HiveJobConfig setPort(String port)
  {
    this.port = port;
    return this;
  }

  public String getDatabase()
  {
    return database;
  }

  public HiveJobConfig setDatabase(String database)
  {
    this.database = database;
    return this;
  }

  public String getUser()
  {
    return user;
  }

  public HiveJobConfig setUser(String user)
  {
    this.user = user;
    return this;
  }

  public String getPassword()
  {
    return password;
  }

  public HiveJobConfig setPassword(String password)
  {
    this.password = password;
    return this;
  }

  public StoreOption getStoreOption()
  {
    return storeOption;
  }

  public boolean isReturningResult()
  {
    return getStoreOption() != StoreOption.NONE;
  }

  public HiveJobConfig setStoreOption(String storeOption)
  {
    return setStoreOption(StoreOption.valueOf(storeOption.toUpperCase()));
  }

  public HiveJobConfig setStoreOption(StoreOption storeOption)
  {
    this.storeOption = storeOption;
    return this;
  }

  public String getStoreVariable()
  {
    return storeVariable;
  }

  public HiveJobConfig setStoreVariable(String storeVariable)
  {
    this.storeVariable = storeVariable;
    if (storeVariable != null && !getStoreVariable().isEmpty() && getStoreOption() == StoreOption.NONE)
    {
      setStoreOption(StoreOption.PROCESS);
    }
    return this;
  }

  public int getMaxResults()
  {
    return maxResults;
  }

  public HiveJobConfig setMaxResults(int maxResults)
  {
    this.maxResults = maxResults;
    return this;
  }

  public HiveJobConfig setMaxResults(String maxResults)
  {
    return setMaxResults(Integer.parseInt(maxResults));
  }
}
