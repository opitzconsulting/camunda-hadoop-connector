package org.camunda.hadoop.sample.predmain.logGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CreateLogDelegate implements JavaDelegate {
  public void execute(DelegateExecution execution) throws Exception {
    File file = new File("../tmp/machinelogs/log" + execution.getVariableLocal("loopCounter") + ".csv");
    file.createNewFile();
    // Zufallszahlen
    double temp = 0.0; // zwischen 90 und 120 ist normal
    double gasPressure = 0.0; // zwischen 20 und 50 mbar
    double voltage = 0.0; // zwischen 70 und 100
    for (int i = 0; i < 1000; i++) {
      temp = Math.random() * 40 + 90;
      gasPressure = Math.random() * 32 + 18;
      voltage = Math.random() * 35 + 70;
      Log log = new Log((i + 1000), temp, gasPressure, voltage);
      printLog(log.toString(), file);
    }
  }

  public void printLog(String l, File file) throws IOException {
    FileWriter print = new FileWriter(file, true);
    print.write(l + "\n");
    print.close();
  }
}
