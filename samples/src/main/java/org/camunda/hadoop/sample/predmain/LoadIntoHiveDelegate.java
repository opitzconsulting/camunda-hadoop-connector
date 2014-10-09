package org.camunda.hadoop.sample.predmain;

/**
 * Probleme bei Benutzerrechten
 * 
 * Lösung: Datei hive-site.xml unter conf-server ändern
 * Authoritätsprüfung ausschalten
 * 
 * <property>
 * 	<name> hive.security.authorization.enabled </name>
 *  <value> false </value>
 * </property>
 */

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.hadoop.hive.AbstractHiveDelegate;
import org.camunda.hadoop.hive.HiveJobConfig;

public class LoadIntoHiveDelegate extends AbstractHiveDelegate {

  @Override
  protected HiveJobConfig configure(DelegateExecution execution,
      HiveJobConfig config) {

    System.out.println("Execute " + this.getClass().getName());

    String path = "/tmp/MachineLogs/PigTransform/part-r-00000";
    
    return config.setQuery("LOAD DATA INPATH '" + path + "' INTO TABLE `default.machinelogs`")
        .setIsUpdate("true")
    		.setHost("192.168.163.128")
    		.setUser("root")
    		.setPassword("hadoop");
  }
}
