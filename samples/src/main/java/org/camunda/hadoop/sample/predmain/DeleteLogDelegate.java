package org.camunda.hadoop.sample.predmain;

import java.io.File;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class DeleteLogDelegate implements JavaDelegate {

		@Override
		public void execute(DelegateExecution execution) throws Exception {
		
			File file = new File((String) execution.getVariable("path"));
			
			if(file.exists())
				file.delete();
			System.out.println("File " + file.getName() + " deleted.");
		}

}

