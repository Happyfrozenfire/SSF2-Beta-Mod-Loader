package com.modloader.btn;

import com.modloader.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CompressFile implements ActionListener
{
  private Component parent;
  
  public CompressFile(Component parent) 
  { 
    this.parent = parent;
  }
  
  @Override
  public void actionPerformed(ActionEvent ae)
  {
    System.out.println("click: compress file");
    File input = null;
    JFileChooser chooser = new JFileChooser(SaveHandler.loadDir());
    chooser.setDialogTitle("Select Decompressed File");
    chooser.setFileFilter(new FileNameExtensionFilter("Shockwave Flash File (.swf)", "swf"));
    int returnval = chooser.showOpenDialog(this.parent);
    if(returnval == JFileChooser.APPROVE_OPTION)
    {
      System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
      input = chooser.getSelectedFile();
      SaveHandler.saveDir(chooser.getSelectedFile().getAbsolutePath());
      File output = null;
      {
        chooser = new JFileChooser(SaveHandler.loadDir());
        chooser.setDialogTitle("Select Location to Compress to");
        chooser.setFileFilter(new FileNameExtensionFilter("SSF2 File (.ssf)", "ssf"));
        returnval = chooser.showOpenDialog(this.parent);
        if(returnval == JFileChooser.APPROVE_OPTION)
        {
          System.out.println("You chose to save to this file: " + chooser.getSelectedFile().getName());
          output = chooser.getSelectedFile();
          SaveHandler.saveDir(chooser.getSelectedFile().getAbsolutePath());
          FileHandler.fileCompress(input, output);
          System.out.println("Mission complete");
          JOptionPane.showMessageDialog(parent, "Compression Complete", "phase-HFF SSF2 Mod Loader", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    }
  }
}