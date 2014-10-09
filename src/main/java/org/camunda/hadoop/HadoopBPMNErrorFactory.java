package org.camunda.hadoop;

import org.camunda.bpm.engine.delegate.BpmnError;

public class HadoopBPMNErrorFactory
{

  public static void throwFileNotFound()
  {
    throw new BpmnError("fileNotFound");
  }

  public static void throwFileAlreadyExists()
  {
    throw new BpmnError("fileAlreadyExists");
  }

  public static void throwDirectoryAlreadyExists()
  {
    throw new BpmnError("directoryAlreadyExists");
  }

  public static void throwNoRecordsFound()
  {
    throw new BpmnError("noRecordsFound");
  }

  public static void throwToManyResults()
  {
    throw new BpmnError("toManyResults");
  }
}
