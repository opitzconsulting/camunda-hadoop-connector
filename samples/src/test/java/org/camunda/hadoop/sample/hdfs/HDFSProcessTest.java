package org.camunda.hadoop.sample.hdfs;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

public class HDFSProcessTest {

	  @Rule
	  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

	  @Test
	  @Deployment(resources = { "hdfs.bpmn" })
	  public void TestHdfsProcess() {
		RuntimeService runtimeService = processEngineRule.getRuntimeService();
		  
		Map<String, Object> variables = new HashMap<String, Object>();
	    variables.put("HDFS Job Command", "createfile");
	    variables.put("HDFS Job Name", "HDFSTest");
 	 
	    runtimeService.startProcessInstanceByKey("HDFS_Test", variables);
	  }
}
