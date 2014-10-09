package org.camunda.hadoop.hive;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class SimpleHiveDelegate extends AbstractHiveDelegate
{

  @Override
  protected HiveJobConfig configure(DelegateExecution execution, HiveJobConfig config)
  {
    return config.setDatabase(findConfVar(execution, "Database", config.getDatabase()))
        .setHost(findConfVar(execution, "Host", config.getHost()))
        .setMaxResults(findConfVar(execution, "Max Results", String.valueOf(config.getMaxResults())))
        .setPassword(findConfVar(execution, "Password", config.getPassword()))
        .setPort(findConfVar(execution, "Port", config.getPort()))
        .setQuery(findConfVar(execution, "Query", config.getQuery()))
        .setIsUpdate(findConfVar(execution, "Update", new Boolean(config.getIsUpdate()).toString()))
        .setStoreOption(findConfVar(execution, "Store Option", config.getStoreOption().toString()))
        .setStoreVariable(findConfVar(execution, "Store Variable", config.getStoreVariable()))
        .setUser(findConfVar(execution, "User", config.getUser()));
  }

}
