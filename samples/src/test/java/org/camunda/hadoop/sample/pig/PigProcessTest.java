package org.camunda.hadoop.sample.pig;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

public class PigProcessTest {

  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

  @Test
  @Deployment(resources = { "pig.bpmn" })
  public void TestPigProcess()
  {
    RuntimeService runtimeService = processEngineRule.getRuntimeService();
    Map<String, Object> variables = new HashMap<String, Object>();
    String query = "A = Load 'Mitarbeiter.csv' using PigStorage(';') AS (id:int, name:chararray, vorname:chararray, gehalt:int); B = Filter A by gehalt >400; C = Group B by name;";
    variables.put("Pig Job Query", query);
    variables.put("Pig Job Store Variable", "C");
    variables.put("Pig Job Store Into Variable", "outputPigResults");
   // runtimeService.startProcessInstanceByKey("Pig_Test", variables);
  }
}