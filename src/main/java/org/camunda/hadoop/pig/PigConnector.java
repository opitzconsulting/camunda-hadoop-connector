package org.camunda.hadoop.pig;

import static org.camunda.hadoop.HadoopBPMNErrorFactory.throwFileAlreadyExists;
import static org.camunda.hadoop.HadoopBPMNErrorFactory.throwFileNotFound;
import static org.camunda.hadoop.HadoopBPMNErrorFactory.throwNoRecordsFound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import org.apache.pig.PigServer;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.backend.executionengine.ExecJob;
import org.apache.pig.data.Tuple;

public class PigConnector
{

  private PigServer pigServer = null;
  private ExecJob execjob = null;

  public void pigQuery(PigJobConfig config) throws ExecException, Exception
  {
    try
    {
      pigConnection(config);
      pigServer.setBatchOn();

      if (config.getQuery() != null)
      {
        // Check file exists.
        String[] splittedQuery = config.getQuery().split("'");

        for (int i = 0; i < splittedQuery.length; i++)
        {
          if (splittedQuery[i].toLowerCase().contains("load"))
          {
            if (!pigServer.existsFile(splittedQuery[i + 1]))
            {
              throwFileNotFound();
            }
          }
        }
        pigServer.registerQuery(config.getQuery());
      }
      else if (config.getFileName() != null && new File(config.getFileName()).exists())
      {
        // Check if file exist
        BufferedReader bReader = new BufferedReader(new FileReader(new File(config.getFileName())));
        String zeile, query = null;

        while ((zeile = bReader.readLine()) != null)
        {
          query = query + zeile;
        }
        bReader.close();

        // Check "Load" and "Store" Files exists
        String[] splittedScript = query.split("'");
        for (int i = 0; i < splittedScript.length; i++)
        {
          if (splittedScript[i].toLowerCase().contains("load"))
          {
            if (!pigServer.existsFile(splittedScript[i + 1]))
            {
              throwFileNotFound();
            }
          }
          if (splittedScript[i].toLowerCase().contains("store") && splittedScript[i].toLowerCase().contains("into"))
          {
            if (pigServer.existsFile(splittedScript[i + 1]))
            {
              throwFileAlreadyExists();
            }
          }
        }
        pigServer.registerScript(config.getFileName());
      }
      else
      {
        throwFileNotFound();
      }

      if (config.getStoreVariable() != null && config.getStoreIntoVariable() != null)
      {
        if (!pigServer.existsFile(config.getStoreIntoVariable()))
        {
          pigServer.store(config.getStoreVariable(), config.getStoreIntoVariable());
        }
        else
        {
          throwFileAlreadyExists();
        }
      }
      else if (config.getStoreVariable() == null && config.getStoreIntoVariable() != null)
      {
        throw new NullPointerException("The storevariable cannot be null!");
      }
      else if (config.getStoreVariable() != null && config.getStoreIntoVariable() == null)
      {
        throw new NullPointerException("The storeIntoVariable cannot be null!");
      }

      execjob = pigServer.executeBatch().get(0);
    } finally
    {
      if (pigServer != null && execjob != null)
      {
        if (config.getOutputTerminal() == true)
        {
          outputTerminal();
        }
      }
    }
  }

  public void pigConnection(PigJobConfig config) throws Exception
  {
    pigServer = new PigServer(config.getExectype());
  }

  public void outputResults(PigJobConfig config) throws ExecException
  {
    if (execjob.getResults().hasNext())
    {
      Iterator<Tuple> results = execjob.getResults();
      Tuple tuple;

      System.out.println("Result: ");

      while (results.hasNext())
      {
        tuple = results.next();
        System.out.println(tuple.toString());
      }
    }
    else
    {
      throwNoRecordsFound();
    }
  }

  public void outputTerminal() throws ExecException
  {
    System.out.println("Completed: " + execjob.hasCompleted());
    System.out.println("Succesful: " + execjob.getStatistics().isSuccessful());
    if (execjob.hasCompleted() && execjob.getStatistics().isSuccessful())
    {
      System.out.println("BytesWritten: " + execjob.getStatistics().getBytesWritten());
      System.out.println("Duration: " + execjob.getStatistics().getDuration());
      System.out.println("Features: " + execjob.getStatistics().getFeatures());
      System.out.println("OutputLocation: " + execjob.getStatistics().getOutputLocations());
      System.out.println("OutputName: " + execjob.getStatistics().getOutputNames());
    }
    else
    {
      System.out.println("ErrorCode: " + execjob.getStatistics().getErrorCode());
      System.out.println("ErrorMessage: " + execjob.getStatistics().getErrorMessage());
    }
  }
}
