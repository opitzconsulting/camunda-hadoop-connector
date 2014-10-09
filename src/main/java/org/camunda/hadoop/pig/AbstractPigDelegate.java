package org.camunda.hadoop.pig;

import javax.naming.ConfigurationException;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.hadoop.HadoopCamundaOperation;

abstract public class AbstractPigDelegate extends HadoopCamundaOperation implements JavaDelegate
{

  abstract protected PigJobConfig configure(DelegateExecution execution, PigJobConfig config);

  @Override
  public void execute(DelegateExecution execution) throws Exception
  {
    PigJobConfig config = configure(execution, new PigJobConfig());
    if (!config.isValid())
    {
      throw new ConfigurationException("No Pig query provided.");
    }
    else
    {
      new PigConnector().pigQuery(config);
    }
  }
}
