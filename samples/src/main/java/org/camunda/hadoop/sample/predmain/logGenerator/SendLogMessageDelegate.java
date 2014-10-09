package org.camunda.hadoop.sample.predmain.logGenerator;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendLogMessageDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
	Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("path", "../tmp/machinelogs/log" + (Integer) execution.getVariableLocal("loopCounter") + ".csv");
    variables.put("counter", (Integer) execution.getVariableLocal("loopCounter"));
    execution.getProcessEngineServices().getRuntimeService().startProcessInstanceByMessage("Machine Log", variables);
  }
}
