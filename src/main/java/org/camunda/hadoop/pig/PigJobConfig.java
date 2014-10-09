package org.camunda.hadoop.pig;

public class PigJobConfig
{

  private String query = null;
  private String fileName = null;
  private String exectype = "MAPREDUCE";
  private String storeVariable = null;
  private String storeIntoVariable = null;
  private boolean outputTerminal = false;

  public boolean isValid()
  {
    return query != null || fileName != null;
  }

  public String getQuery()
  {
    return query;
  }

  public PigJobConfig setQuery(String query)
  {
    this.query = query;
    return this;
  }

  public String getFileName()
  {
    return fileName;
  }

  public PigJobConfig setFileName(String path)
  {
    fileName = path;
    return this;
  }

  public String getExectype()
  {
    return exectype;
  }

  public PigJobConfig setExectype(String ex)
  {
    exectype = ex.toUpperCase();
    return this;
  }

  public PigJobConfig setStoreVariable(String name)
  {
    storeVariable = name;
    return this;
  }

  public String getStoreVariable()
  {
    return storeVariable;
  }

  public PigJobConfig setStoreIntoVariable(String name)
  {
    storeIntoVariable = name;
    return this;
  }

  public String getStoreIntoVariable()
  {
    return storeIntoVariable;
  }

  public PigJobConfig setOutputTerminal(String b)
  {
    outputTerminal = Boolean.valueOf(b);
    return this;
  }

  public boolean getOutputTerminal()
  {
    return outputTerminal;
  }
}