package org.camunda.hadoop.hdfs;

import javax.naming.ConfigurationException;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.hadoop.HadoopCamundaOperation;

public abstract class AbstractHDFSDelegate extends HadoopCamundaOperation implements JavaDelegate
{

  abstract protected HDFSJobConfig configure(DelegateExecution execution, HDFSJobConfig config);

  @Override
  public void execute(DelegateExecution execution) throws Exception
  {
    HDFSJobConfig config = configure(execution, new HDFSJobConfig());

    if (!config.isValid())
    {
      throw new ConfigurationException("No command provided.");
    }

    new HDFSConnector().executeStatement(config);
  }
}
