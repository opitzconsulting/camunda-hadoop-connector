package org.camunda.hadoop.hdfs;

import static org.camunda.hadoop.HadoopBPMNErrorFactory.throwDirectoryAlreadyExists;
import static org.camunda.hadoop.HadoopBPMNErrorFactory.throwFileAlreadyExists;
import static org.camunda.hadoop.HadoopBPMNErrorFactory.throwFileNotFound;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class HDFSConnector
{

  private FileSystem fileSystem = null;
  private Configuration conf = null;

  public void executeStatement(HDFSJobConfig config) throws Exception
  {
    switch (config.getStatementOption())
    {
      case DELETE:
        deleteFile(config);
        break;
      case CREATEDIRECTORY:
        createDirectory(config);
        break;
      case CREATEFILE:
        createFile(config);
        break;
      case DOWNLOAD:
        downloadFile(config);
        break;
      case UPLOAD:
        uploadFile(config);
        break;
      case MOVE:
        moveFile(config);
        break;
      case COPY:
        copyFile(config);
        break;
      case NONE:
    }
  }

  /*
   * Delete a file
   */
  public void deleteFile(HDFSJobConfig config) throws Exception
  {
    try
    {
      // Create a new Configuration object it will automatically load the
      // default-configuration-files.
      conf = new Configuration();
      fileSystem = FileSystem.get(conf);
      Path path = null;

      if (config.nameIsValid())
      {
        path = new Path(config.getName());
      }
      else
      {
        throw new NullPointerException("The filename cannot be null!");
      }

      if (!fileSystem.exists(path))
      {
        throwFileNotFound();
      }
      else
      {
        fileSystem.delete(path, true);
        System.out.println("File " + config.getName() + " deleted.");
      }
    } finally
    {
      fileSystem.close();
    }
  }

  /*
   * Create a new file
   */
  public void createFile(HDFSJobConfig config) throws Exception
  {
    try
    {
      conf = new Configuration();
      fileSystem = FileSystem.get(conf);

      Path path = null;

      if (config.nameIsValid())
      {
        path = new Path(config.getName());
      }
      else
      {
        throw new NullPointerException("The filename cannot be null!");
      }

      // Check if the file already exists
      if (fileSystem.exists(path))
      {
        throwFileAlreadyExists();
      }
      else
      {
        // Create a new file
        fileSystem.create(path);
        System.out.println("File " + config.getName() + " generated.");
      }
    } finally
    {
      fileSystem.close();
    }
  }

  /*
   * Create a new Directory
   */
  public void createDirectory(HDFSJobConfig config) throws Exception
  {
    try
    {
      conf = new Configuration();
      fileSystem = FileSystem.get(conf);

      Path path = null;

      if (config.nameIsValid())
      {
        path = new Path(config.getName());
      }
      else
      {
        throw new NullPointerException("The directoryname cannot be null!");
      }

      if (fileSystem.exists(path))
      {
        throwDirectoryAlreadyExists();
      }
      else
      { // Create a new Directory
        fileSystem.mkdirs(path);
        System.out.println("Directory " + config.getName() + " generated.");
      }
    } finally
    {
      fileSystem.close();
    }
  }

  /*
   * Download a file from hdfs Nicht vorhandene Ordner werden erstellt, Datei
   * kann auch direkt benannt werden
   */
  public void downloadFile(HDFSJobConfig config) throws Exception
  {
    try
    {
      conf = new Configuration();
      conf.addResource(new Path("/home/hadoop/hadoop/conf/core-site.xml"));
      conf.addResource(new Path("/home/hadoop/hadoop/conf/hdfs-site.xml"));

      fileSystem = FileSystem.get(conf);
      FileSystem fileSystem1 = FileSystem.getLocal(conf);

      Path pathSrc = null;
      Path pathDst = null;

      if (config.pathIsValid())
      {
        pathSrc = new Path(config.getSource());
        pathDst = new Path(config.getTarget());
      }
      else if (config.getSource() == null)
      {
        throw new NullPointerException("The Sourcepath cannot be null!");
      }
      else
      {
        throw new NullPointerException("The Targetpath cannot be null!");
      }

      if (fileSystem1.exists(pathDst))
      {
        throwFileAlreadyExists();
      }
      else if (!fileSystem.exists(pathSrc))
      {
        throwFileNotFound();
      }
      else
      {
        // Copy the file
        fileSystem.copyToLocalFile(pathSrc, pathDst);
        System.out.println("File " + config.getSource() + " copied to " + config.getTarget());
      }
    } finally
    {
      fileSystem.close();
    }
  }

  /*
   * Upload a file into hdfs
   */
  public void uploadFile(HDFSJobConfig config) throws Exception
  {
    try
    {
      conf = new Configuration();
      conf.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
      conf.addResource(new Path("/etc/hadoop/hadoop/conf/hdfs-site.xml"));

      fileSystem = FileSystem.get(conf);
      FileSystem fileSystem1 = FileSystem.getLocal(conf); // local

      Path pathSrc = null;
      Path pathDst = null;

      if (config.pathIsValid())
      {
        pathSrc = new Path(config.getSource());
        pathDst = new Path(config.getTarget());
      }
      else if (config.getSource() == null)
      {
        throw new NullPointerException("The Sourcepath cannot be null!");
      }
      else
      {
        throw new NullPointerException("The Targetpath cannot be null!");
      }

      if (fileSystem.exists(pathDst))
      {
        throwFileAlreadyExists();
      }
      else if (!fileSystem1.exists(pathSrc))
      {
        throwFileNotFound();
      }
      else
      { // Copy the file
        fileSystem.copyFromLocalFile(false, true, pathSrc, pathDst);
        System.out.println("File " + config.getSource() + " upload to " + config.getTarget());
      }
    } finally
    {
      fileSystem.close();
    }
  }

  public void moveFile(HDFSJobConfig config) throws Exception
  {
    try
    {
      conf = new Configuration();
      fileSystem = FileSystem.get(conf);

      Path pathSrc = null;
      Path pathDst = null;

      if (config.pathIsValid())
      {
        pathSrc = new Path(config.getSource());
        pathDst = new Path(config.getTarget());
      }
      else if (config.getSource() == null)
      {
        throw new NullPointerException("The Sourcepath cannot be null!");
      }
      else
      {
        throw new NullPointerException("The Targetpath cannot be null!");
      }

      if (fileSystem.exists(pathDst))
      {
        throwFileAlreadyExists();
      }
      else if (!fileSystem.exists(pathSrc))
      {
        throwFileNotFound();
      }
      else
      {
        fileSystem.rename(pathSrc, pathDst);
        System.out.println("File " + config.getSource() + " moved to " + config.getTarget());
      }
    } finally
    {
      fileSystem.close();
    }
  }

  public void copyFile(HDFSJobConfig config) throws Exception
  {
    try
    {
      conf = new Configuration();
      fileSystem = FileSystem.get(conf);

      Path pathSrc = new Path(config.getSource());
      Path pathDst = new Path(config.getTarget());

      if (config.pathIsValid())
      {
        pathSrc = new Path(config.getSource());
        pathDst = new Path(config.getTarget());
      }
      else if (config.getSource() == null)
      {
        throw new NullPointerException("The Sourcepath cannot be null!");
      }
      else
      {
        throw new NullPointerException("The CopyPath cannot be null!");
      }

      if (fileSystem.exists(pathDst))
      {
        throwFileAlreadyExists();
      }
      else if (fileSystem.exists(pathSrc))
      {
        throwFileNotFound();
      }
      else
      {
        FileUtil.copy(fileSystem, pathSrc, fileSystem, pathDst, false,
            conf); // false, da die Datei sonst gelöscht wird.
        System.out.println("File " + config.getSource() + " copied to " + config.getTarget());
      }
    } finally
    {
      fileSystem.close();
    }
  }
}