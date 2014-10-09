package org.camunda.hadoop.sample.predmain;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.hadoop.pig.AbstractPigDelegate;
import org.camunda.hadoop.pig.PigJobConfig;

public class TransformLogsDelegate extends AbstractPigDelegate {

	@Override
	protected PigJobConfig configure(DelegateExecution execution,
			PigJobConfig config) {
		System.out.println("Execute " + this.getClass().getName());
		
		String query = "A = load '/tmp/MachineLogs' using PigStorage(';') AS (date:chararray, machine:int, temp:float, pressure:float, voltage:float); " 
      		+ "B = load 'Clients.csv' using PigStorage(';') AS (client:int, rating:int); "
      		+ "C = Load 'Sales.csv' using PigStorage (';') AS (saleId: chararray, clientId:int, machineID:int);" 
      		+ "D = Join B by client, C by clientId;"
      		+ "E = Join A by machine, D by machineID;"
      		+ "F = Foreach E generate date, machine, temp, pressure, voltage, client, rating, saleId;";

		return config.setQuery(query)
		        .setStoreVariable("F")
		        .setStoreIntoVariable("/tmp/MachineLogs/PigTransform");
	}
}
