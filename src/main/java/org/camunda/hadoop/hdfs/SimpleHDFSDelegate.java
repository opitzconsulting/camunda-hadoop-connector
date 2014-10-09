package org.camunda.hadoop.hdfs;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class SimpleHDFSDelegate extends AbstractHDFSDelegate
{

  @Override
  protected HDFSJobConfig configure(DelegateExecution execution, HDFSJobConfig config)
  {
    return config.setStatementOption(findConfVar(execution, "Command", config.getStatementOption().toString()))
        .setName(findConfVar(execution, "Name", config.getName()))
        .setSource(findConfVar(execution, "Source", config.getSource()))
        .setTarget(findConfVar(execution, "Target", config.getTarget()));
  }
}