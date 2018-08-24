package com.modloader;

import com.modloader.btn.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable
{
  private JFrame frame;
  
  public UserInterface() {  }
  
  @Override
  public void run() 
  {
    try { UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel"); }
    catch(Exception e) { System.out.println("Could not find HiFiLookAndFeel"); }
    this.frame = new JFrame("phase-HFF SSF2 Mod Loader");
    this.frame.setPreferredSize(new Dimension(300, 400));
    this.frame.setResizable(false);
    
    this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    createComponents(frame.getContentPane());
    
    this.frame.pack();
    this.frame.setLocationRelativeTo(null);
    this.frame.setVisible(true);
  }
  
  private void createComponents(Container container) 
  {  
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    
    
    JTextPane text = new JTextPane();
    text.setText("phase's SSF2 Mod Loader\n" +
                  "with contributions from Happyfrozenfire\n\n\n" + 
                  "Decompress will convert .ssf files into\n" +
                  ".swf files that you can open in JPEXS.\n\n" +
                  "Compress will convert .swf files into\n" +
                  ".ssf files that the game can read.\n\n" +
                  "Each file button will allow you to\n" +
                  "convert a single file at a time and\n" +
                  "deposit them in a location of your choice.\n\n" +
                  "Each batch button will convert the\n" +
                  "contents of a folder of your choice and\n" +
                  "deposit them in that folder.");
    text.setEditable(false);
    
    
    JPanel interactionsPanel = new JPanel(new GridLayout(1, 2));
    
    JPanel decompressPanel = new JPanel(new GridLayout(2, 1));
    JPanel decompressButtonPanel = new JPanel(new GridLayout(1, 2));
    JButton btn_decompressFile = new JButton("file");
    DecompressFile act_decompressFile = new DecompressFile(container);
    btn_decompressFile.addActionListener(act_decompressFile);
    decompressButtonPanel.add(btn_decompressFile);
    JButton btn_decompressBatch = new JButton("batch");
    DecompressBatch act_decompressBatch = new DecompressBatch(container);
    btn_decompressBatch.addActionListener(act_decompressBatch);
    decompressButtonPanel.add(btn_decompressBatch);
    decompressPanel.add(new JLabel("DECOMPRESS"));
    decompressPanel.add(decompressButtonPanel);
    
    JPanel compressPanel = new JPanel(new GridLayout(2, 1));
    JPanel compressButtonPanel = new JPanel(new GridLayout(1, 2));
    JButton btn_compressFile = new JButton("file");
    CompressFile act_compressFile = new CompressFile(container);
    btn_compressFile.addActionListener(act_compressFile);
    compressButtonPanel.add(btn_compressFile);
    JButton btn_compressBatch = new JButton("batch");
    CompressBatch act_compressBatch = new CompressBatch(container);
    btn_compressBatch.addActionListener(act_compressBatch);
    compressButtonPanel.add(btn_compressBatch);
    compressPanel.add(new JLabel("COMPRESS"));
    compressPanel.add(compressButtonPanel);
    
    interactionsPanel.add(decompressPanel);
    interactionsPanel.add(compressPanel);
    
    
    JTextPane help = new JTextPane();
    help.setText("Decompress will convert .ssf files into\n" +
                 ".swf files that you can open in JPEXS.\n\n" +
                 "Compress will convert .swf files into\n" +
                 ".ssf files that the game can read.\n\n" +
                 "Each file button will allow you to\n" +
                 "convert a single file at a time and\n" +
                 "deposit them in a location of your choice.\n\n" +
                 "Each batch button will convert the\n" +
                 "contents of a folder of your choice and\n" +
                 "deposit them in that folder.");
    help.setEditable(false);
    
    
    container.add(text);
    container.add(interactionsPanel);
  }
  
  public JFrame getFrame() { return this.frame; }
  
  public int getInt() { return 0; }
  
}