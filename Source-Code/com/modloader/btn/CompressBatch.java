package com.modloader.btn;

import com.modloader.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CompressBatch implements ActionListener
{
  private Component parent;
  
  public CompressBatch(Component parent) 
  { 
    this.parent = parent;
  }
  
  @Override
  public void actionPerformed(ActionEvent ae)
  {
    System.out.println("click: compress batch");
    File directory = null;
    JFileChooser chooser = new JFileChooser(SaveHandler.loadDir());
    chooser.setDialogTitle("Select data folder");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setAcceptAllFileFilterUsed(false);
    int returnval = chooser.showOpenDialog(this.parent);
    if(returnval == JFileChooser.APPROVE_OPTION)
    {
      System.out.println("You selected this directory: " + chooser.getSelectedFile());
      directory = chooser.getSelectedFile();
      SaveHandler.saveDir(chooser.getSelectedFile().getAbsolutePath());
      File[] files = directory.listFiles
      (
        new FilenameFilter() 
        {
          public boolean accept(File dir, String name) 
          {
            return name.toLowerCase().endsWith(".swf");
          }
        }
      );
      for(File file : files)
      {
        FileHandler.fileCompress(file, new File(file.getPath().substring(0, file.getPath().length() - 4) + ".ssf"));
      }
      System.out.println("Mission complete");
      JOptionPane.showMessageDialog(parent, "Compression Complete", "phase-HFF SSF2 Mod Loader", JOptionPane.INFORMATION_MESSAGE);
    }
  }
}