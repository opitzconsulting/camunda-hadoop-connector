package org.camunda.hadoop.sample.predmain;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendNewMachineOfferDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    // TODO Auto-generated method stub
    System.out.println("Execute " + this.getClass().getName());
  }

}
