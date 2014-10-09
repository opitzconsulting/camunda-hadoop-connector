package org.camunda.hadoop.sample;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ErrorHandlingFileExists implements JavaDelegate 
{
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("Oh, this file already exists :)");
	}

}
