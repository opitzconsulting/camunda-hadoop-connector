package org.camunda.hadoop.sample.predmain;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.hadoop.hive.AbstractHiveDelegate;
import org.camunda.hadoop.hive.HiveJobConfig;

public class QueryHighTemperatureDelegate extends AbstractHiveDelegate {
	
	@Override
	protected HiveJobConfig configure(DelegateExecution execution,
			HiveJobConfig config) {
	  
		System.out.println("Execute " + this.getClass().getName());
		
		return config.setQuery("select machineid, client, rating, AVG(temperature) as avgTemp, MAX(temperature) as MaxTemp"
					+ " from machinelogs"
					+ " where from_unixtime(cast((date/1000) as BIGINT))"
					+ " >= Date_ADD(from_unixtime(unix_timestamp()), -1)"
					+ " group by machineid, client, rating "
					+ " having AVG(temperature) > 113 ")
				.setStoreVariable("resultsHighTemperature")
				.setHost("192.168.163.128")
	    	.setUser("root")
	    	.setPassword("hadoop");
	}	
}
