package org.camunda.hadoop.sample.hive;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.hadoop.hive.AbstractHiveDelegate;
import org.camunda.hadoop.hive.HiveJobConfig;

public class SampleHiveDelegate extends AbstractHiveDelegate {

	
  @Override
  protected HiveJobConfig configure(DelegateExecution execution, HiveJobConfig config) {
	 return config.setDatabase(findConfVar(execution, "database", config.getDatabase()))
        .setHost(findConfVar(execution, "host", config.getHost()))
        .setPassword(findConfVar(execution, "password", config.getPassword()))
        .setPort(findConfVar(execution, "port", config.getPort()))
        .setQuery(findConfVar(execution, "query", config.getQuery()))
        .setStoreOption(findConfVar(execution, "store option", config.getStoreOption().toString()))
        .setStoreVariable(findConfVar(execution, "store variable", config.getStoreVariable()))
        .setUser(findConfVar(execution, "user", config.getUser()));  
  }
}
