package com.modloader;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class SaveHandler
{
  public static void saveDir(String directory)
  {
    Properties prop = new Properties();
    try
    {
      FileOutputStream output = new FileOutputStream("config.properties");
      prop.setProperty("dir", directory);
      prop.store(output, null);
      output.close();
      System.out.println("Saved directory " + directory + "\nWill load it next time!");
    }
    catch(Exception e)
    {
      System.out.println("Couldn't save last directory.");
    }
  }
      
  public static String loadDir()
  {
    Properties prop = new Properties();
    String str = "";
    try
    {
      FileInputStream input = new FileInputStream("config.properties");
      prop.load(input);
      System.out.println("Loaded directory " + prop.getProperty("dir"));
      str = prop.getProperty("dir");
    }
    catch(Exception e)
    {
      System.out.println("Couldn't load last directory.");
      str = System.getProperty("user.home");
    }
    return str;
  }
}