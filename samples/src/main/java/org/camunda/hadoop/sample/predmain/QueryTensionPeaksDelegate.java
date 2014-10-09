package org.camunda.hadoop.sample.predmain;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.hadoop.hive.AbstractHiveDelegate;
import org.camunda.hadoop.hive.HiveJobConfig;

public class QueryTensionPeaksDelegate extends AbstractHiveDelegate {
	
	@Override
	protected HiveJobConfig configure(DelegateExecution execution,
			HiveJobConfig config) {
	    System.out.println("Execute " + this.getClass().getName());

	    return config.setQuery("select machineid, client, rating, MAX(voltage) as MaxVoltage, count(voltage) as CountVoltage"
					    		+ " from machinelogs"
					    		+ " where voltage > 100"
					    		+ " and from_unixtime(cast((date/1000) as BIGINT))"
					    		+ " >= Date_ADD(from_unixtime(unix_timestamp()), -2)" //last two days
					    		+ " group by machineid, client, rating"
					    		+ " having count(voltage>100)>15")
			    .setStoreVariable("resultsTensionPeaks")
	    		.setHost("192.168.163.128")
	    		.setUser("root")
	    		.setPassword("hadoop");
	}
}
