package org.camunda.hadoop.hive;

import java.sql.SQLException;
import java.util.List;

import javax.naming.ConfigurationException;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.hadoop.HadoopCamundaOperation;
import org.camunda.hadoop.hive.HiveJobConfig.StoreOption;

public abstract class AbstractHiveDelegate extends HadoopCamundaOperation implements JavaDelegate
{

  abstract protected HiveJobConfig configure(DelegateExecution execution, HiveJobConfig config);

  @Override
  public void execute(DelegateExecution execution) throws SQLException, ConfigurationException, ClassNotFoundException
  {

    HiveJobConfig config = configure(execution, new HiveJobConfig());

    if (!config.isValid())
    {
      throw new ConfigurationException("No Hive query provided.");
    }

    List<String[]> resultList = new HiveConnector().query(config);
    if (config.isReturningResult())
    {
      Object result = resultList;
      if (resultList.size() == 1)
      {
        String[] firstLine = resultList.get(0);
        result = firstLine.length == 1 ? firstLine[0] : firstLine;
      }
      if (config.getStoreOption() == StoreOption.LOCAL)
      {
        execution.setVariableLocal(config.getStoreVariable(), result);
      }
      else
      {
        execution.setVariable(config.getStoreVariable(), result);
      }
    }
  }
}
