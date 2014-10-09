package org.camunda.hadoop.sample.hive;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PrintHiveResult implements JavaDelegate {

  @SuppressWarnings("unchecked")
  public void execute(DelegateExecution execution) throws Exception {
	  
    Object o = execution.getVariable("result");
    if (o instanceof List) {
      for (String[] a : (List<String[]>) o) {
        for (String s : a)
          System.out.print(s + " | ");
        System.out.println();
      }
    } else if (o instanceof String[]) {
      for (String s : (String[]) o)
        System.out.print(s + " | ");
      System.out.println();
    } else if (o instanceof String) {
      System.out.println(o);
    }
  }

}
