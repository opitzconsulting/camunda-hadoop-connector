package org.camunda.hadoop.sample.predmain;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.hadoop.hive.AbstractHiveDelegate;
import org.camunda.hadoop.hive.HiveJobConfig;

public class QueryLowPressureDelegate extends AbstractHiveDelegate {

	@Override
	protected HiveJobConfig configure(DelegateExecution execution,
			HiveJobConfig config) {
	    
		System.out.println("Execute " + this.getClass().getName());
		
		int minPressure = 19;
	    return config.setQuery("select machineid, client, rating, Min(pressure) as MinPressure"
	    							+ " from machinelogs"
	    							+ " where pressure < " + minPressure 
	    							+ " and from_unixtime(cast((date/1000) as BIGINT))"
						    		+ " >= Date_ADD(from_unixtime(unix_timestamp()), -1)" 
	    							+ " group by machineid, client, rating"
	    							+ " having count(pressure<" + minPressure +")>10")
	    		.setStoreVariable("resultsLowPressure")
	    		.setHost("192.168.163.128")
	    		.setUser("root")
	    		.setPassword("hadoop");
	    }
}
