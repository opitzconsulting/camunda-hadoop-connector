package org.camunda.hadoop.sample.predmain;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.hadoop.hdfs.AbstractHDFSDelegate;
import org.camunda.hadoop.hdfs.HDFSJobConfig;

public class MoveLogsToArchiveDelegate extends AbstractHDFSDelegate{

	@Override
	protected HDFSJobConfig configure(DelegateExecution execution,
			HDFSJobConfig config) {
		return config.setStatementOption("move")
		        .setSource("/tmp/MachineLogs")
		        .setTarget("/tmp/MachineLogsArchive/Logs" + execution.getProcessInstanceId());
	}
}
