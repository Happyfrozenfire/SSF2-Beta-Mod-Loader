package com.modloader.btn;

import com.modloader.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DecompressFile implements ActionListener
{
  private Component parent;
  
  public DecompressFile(Component parent) 
  { 
    this.parent = parent;
  }
  
  @Override
  public void actionPerformed(ActionEvent ae)
  {
    System.out.println("click: decompress file");
    File input = null;
    JFileChooser chooser = new JFileChooser(SaveHandler.loadDir());
    chooser.setDialogTitle("Select Compressed File");
    chooser.setFileFilter(new FileNameExtensionFilter("SSF2 File (.ssf)", "ssf"));
    int returnval = chooser.showOpenDialog(this.parent);
    if(returnval == JFileChooser.APPROVE_OPTION)
    {
      System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
      input = chooser.getSelectedFile();
      SaveHandler.saveDir(chooser.getSelectedFile().getAbsolutePath());
      File output = null;
      {
        chooser = new JFileChooser(SaveHandler.loadDir());
        chooser.setDialogTitle("Select Location to Decompress to");
        chooser.setFileFilter(new FileNameExtensionFilter("Shockwave Flash File (.swf)", "swf"));
        returnval = chooser.showOpenDialog(this.parent);
        if(returnval == JFileChooser.APPROVE_OPTION)
        {
          System.out.println("You chose to save to this file: " + chooser.getSelectedFile().getName());
          output = chooser.getSelectedFile();
          FileHandler.fileDecompress(input, output);
          SaveHandler.saveDir(chooser.getSelectedFile().getAbsolutePath());
          System.out.println("Mission complete");
          JOptionPane.showMessageDialog(parent, "Decompression Complete", "phase-HFF SSF2 Mod Loader", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
          System.out.println("Cancelled operation");
        }
      }
      
    }
    else
    {
      System.out.println("Cancelled operation");
    }
  }
}