package org.camunda.hadoop.sample.predmain;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class EvaluateSupportContractDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
     System.out.println("Execute " + this.getClass().getName());
     
     Object o = execution.getVariableLocal("machineData");
     
     String [] log = (String[]) o;
     execution.setVariableLocal("rating", log[2]);		  
  }
}
