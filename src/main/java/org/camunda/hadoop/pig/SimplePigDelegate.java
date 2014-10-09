package org.camunda.hadoop.pig;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class SimplePigDelegate extends AbstractPigDelegate
{

  @Override
  protected PigJobConfig configure(DelegateExecution execution, PigJobConfig config)
  {
    return config
        .setQuery(findConfVar(execution, "Query", config.getQuery()))
        .setStoreVariable(findConfVar(execution, "Store Variable", config.getStoreVariable()))
        .setStoreIntoVariable(findConfVar(execution, "Store Into Variable", config.getStoreIntoVariable()))
        .setExectype(findConfVar(execution, "Exectype", config.getExectype()))
        .setFileName(findConfVar(execution, "Script", config.getFileName()))
        .setOutputTerminal(
            findConfVar(execution, "Output Terminal", new Boolean(config.getOutputTerminal()).toString()));
  }
}
