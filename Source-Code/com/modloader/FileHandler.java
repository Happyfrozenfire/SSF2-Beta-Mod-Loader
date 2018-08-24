package com.modloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.Integer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.zip.InflaterInputStream;
import java.util.zip.DeflaterOutputStream;

public class FileHandler
{
  public static void fileDecompress(File path, File targetPath)
  {
    try
    {
      FileInputStream input = new FileInputStream(path);
      InflaterInputStream decompressor = new InflaterInputStream(input);
      FileOutputStream output = new FileOutputStream(targetPath);
      int data;
      ArrayList<Integer> byteArrayList = new ArrayList<Integer>();
      System.out.println("Decompressing " + path.getName());
      while((data = decompressor.read()) != -1)
      {
        byteArrayList.add(new Integer(data));
      }
      input.close();
      decompressor.close();
      int offset = -1;
      for(int i = 0; i < byteArrayList.size() - 2; i++)
      {
        if(byteArrayList.get(i).intValue() == 0x46 && byteArrayList.get(i + 1).intValue() == 0x57 && byteArrayList.get(i + 2).intValue() == 0x53)
        {
          offset = i;
        }
      }
      if(offset == -1)
      {
        System.out.println("ERROR, FILE " + path.getName() + " NOT RECOGNIZED AS SWF");
        output.close();
        return;
      }
      for(int i = 0; i < offset; i++)
      {
        byteArrayList.remove(0);
      }
      for(int i = 0; i < byteArrayList.size(); i++)
      {
        output.write(byteArrayList.get(i).intValue());
      }
      output.close();
      System.out.println("Decompressed " + path.getName());
    }
    catch(Exception e)
    {
      System.out.println("Something went wrong.");
    }
  }
  
  public static void fileCompress(File path, File targetPath)
  {
    try
    {
      FileInputStream input = new FileInputStream(path);
      System.out.println("Compressing " + path.getName());
      int data;
      ArrayList<Integer> byteArrayList = new ArrayList<Integer>();
      while((data = input.read()) != -1)
      {
        byteArrayList.add(new Integer(data));
      }
      input.close();
      byte[] bytes = ByteBuffer.allocate(4).putInt(byteArrayList.size()).array();
      for(int i = 0; i < 4; i++)
      {
        byteArrayList.add(0, new Integer(0));
      }
      for(int i = 0; i < 4; i++)
      {
        byteArrayList.add(i, new Integer((int)bytes[i]));
      }
      FileOutputStream output = new FileOutputStream(targetPath);
      DeflaterOutputStream compressor = new DeflaterOutputStream(output);
      for(int i = 0; i < byteArrayList.size(); i++)
      {
        compressor.write(byteArrayList.get(i).intValue());
      }
      compressor.close();
      System.out.println("Compressed " + path.getName());
    }
    catch(Exception e)
    {
      System.out.println("Something went wrong.");
    }
  }
}