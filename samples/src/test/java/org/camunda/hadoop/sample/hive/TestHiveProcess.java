package org.camunda.hadoop.sample.hive;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

public class TestHiveProcess {

  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

  @Test
  @Deployment(resources = { "hive.bpmn" })
  public void TestHdfsProcess() {
    RuntimeService runtimeService = processEngineRule.getRuntimeService();

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("hive job query", "select * from sample_08 limit 20");
    variables.put("hive job host", "192.168.163.128");
    variables.put("hive job user", "root");
    variables.put("hive job password", "hadoop");
    variables.put("hive job store variable", "result");
    
    runtimeService.startProcessInstanceByKey("Hive_Test", variables);
  }
}
