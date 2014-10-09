package org.camunda.hadoop.sample.predmain.logGenerator;

public class Log {

  private int machineId = 0;
  private double temperature = 0;
  private double gasPressure = 0;
  private double voltage = 0;
  private long timestamp = System.currentTimeMillis();

  Log(int id, double temp, double gas, double voltage)
  {
    this.machineId = id;
    this.temperature = temp;
    this.gasPressure = gas;
    this.voltage = voltage;
  }

  public int getMachineId()
  {
    return machineId;
  }

  public double getTemperature()
  {
    return temperature;
  }

  public double getGasPressure()
  {
    return gasPressure;
  }

  public double getVoltage()
  {
    return voltage;
  }

  public String toString()
  {
    return timestamp + ";" + machineId + ";" + temperature + ";" + gasPressure + ";" + voltage;
  }
}
