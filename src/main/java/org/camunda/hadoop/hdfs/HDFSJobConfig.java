package org.camunda.hadoop.hdfs;

public class HDFSJobConfig
{

  public enum StatementOption
  {
    DELETE, CREATEDIRECTORY, CREATEFILE, DOWNLOAD, UPLOAD, MOVE, COPY, NONE
  }

  private StatementOption command = StatementOption.NONE;
  private String name = null;
  private String src = null;
  private String target = null;

  public boolean isValid()
  {
    return command != StatementOption.NONE;
  }

  public boolean nameIsValid()
  {
    return name != null;
  }

  public boolean pathIsValid()
  {
    return src != null && target != null;
  }

  public HDFSJobConfig setStatementOption(String stmtOption)
  {
    return setStatementOption(StatementOption.valueOf(stmtOption.toUpperCase()));
  }

  public HDFSJobConfig setStatementOption(StatementOption stmtOption)
  {
    command = stmtOption;
    return this;
  }

  public StatementOption getStatementOption()
  {
    return command;
  }

  public HDFSJobConfig setName(String name)
  {
    this.name = name;
    return this;
  }

  public String getName()
  {
    return name;
  }

  public HDFSJobConfig setSource(String src)
  {
    this.src = src;
    return this;
  }

  public String getSource()
  {
    return src;
  }

  public HDFSJobConfig setTarget(String target)
  {
    this.target = target;
    return this;
  }

  public String getTarget()
  {
    return target;
  }
}
