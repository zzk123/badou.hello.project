package com.badou.start;
 
import javax.swing.*;

import com.sun.awt.AWTUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
 
/**
 *
 *
 *
 */
 
public class test extends JFrame  {
 
	public test() {  
        super("Minimal-Frame-Application");  
          
        // setup menu  
        JMenuBar menuBar = new JMenuBar();  
        JMenu menu = new JMenu("File");  
        menu.setMnemonic('F');  
        JMenuItem menuItem = new JMenuItem("Exit");  
        menuItem.setMnemonic('x');  
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));  
        menuItem.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent event) {  
                System.exit(0); } }); menu.add(menuItem); menuBar.add(menu); setJMenuBar(menuBar); // setup widgets  
        JPanel contentPanel = new JPanel(new BorderLayout());  
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));  
        JScrollPane westPanel = new JScrollPane(new JTree());  
        JEditorPane editor = new JEditorPane("text/plain", "Hello World");  
        JScrollPane eastPanel = new JScrollPane(editor);  
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, westPanel,eastPanel);  
        splitPane.setDividerLocation(148);  
        contentPanel.add(splitPane, BorderLayout.CENTER);  
        setContentPane(contentPanel);  
          
        // add listeners  
        addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent e) {  
                System.exit(0);  
            }  
        });  
          
        // show application  
        setLocation(32, 32);  
        setSize(400, 300);  
        show();  
    } // end CTor MinFrame  
      
    public static void main(String[] args) {  
        try {  
            // select Look and Feel  
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");  
            // start application  
            new test();  
        }  
        catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    } // end main 
}