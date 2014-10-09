package org.camunda.hadoop;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class HadoopCamundaOperation
{

  protected String findConfVar(DelegateExecution execution, String name)
  {
    return findConfVar(execution, name, null);
  }

  protected String findConfVar(DelegateExecution execution, String name, String defaultVal)
  {
    String var = execution.getCurrentActivityName() + " " + name;
    String[] varNames = {
        var,
        var.toLowerCase(),
        var.replace(" ", "_"),
        var.replace(" ", "_").toLowerCase(),
        var.replace(" ", ""),
        var.replace(" ", "").toLowerCase()
    };
    for (String varName : varNames)
    {
      if (execution.getVariable(varName) != null)
      {
        return execution.getVariable(varName).toString();
      }
      else if (execution.getVariableLocal(varName) != null)
      {
        return execution.getVariableLocal(varName).toString();
      }
    }
    return defaultVal;
  }

}
