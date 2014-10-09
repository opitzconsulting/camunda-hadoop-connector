package org.camunda.hadoop.sample.predmain;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.hadoop.hdfs.AbstractHDFSDelegate;
import org.camunda.hadoop.hdfs.HDFSJobConfig;

public class UploadLogDelegate extends AbstractHDFSDelegate {

  @Override
  protected HDFSJobConfig configure(DelegateExecution execution, HDFSJobConfig config) {
    System.out.println("Execute " + this.getClass().getName());
    return config.setStatementOption("upload")
        .setSource((String) execution.getVariable("path"))
        .setTarget("/tmp/MachineLogs/log" + execution.getVariableLocal("counter") + ".csv");
  }
}
