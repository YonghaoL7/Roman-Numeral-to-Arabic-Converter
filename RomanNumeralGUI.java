package project4;

import java.awt.*;
import javax.swing.*;

/**
 * Creates a GUI with a menu with options to convert Roman numeral strings into Arabic values which are then displayed
 * @author Yonghao
 *
 */
public class RomanNumeralGUI extends JFrame{
   
   /**
    * Creates a RomanNumeralGUI object with a set size, location, and title. Calls on the createMenu() method to create a menu in the GUI
    */
   public RomanNumeralGUI() {
      setSize(800, 500);
      setLocation(100, 100);
      setTitle("Roman Numerals");
      createMenu(); 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   /**
    * Creates a menu bar inside of the GUI with two menus: File and Convert
    * The File menu contains the menu items: Open and Close
    * The Convert menu contains the menu item: Roman to Arabic
    * All the menu items are set to have ActionListeners
    */
   public void createMenu() {
      //Creates the menu bar
      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      
      //Creates the File menu, menu items, and initializes them with action listeners
      JMenu fileMenu = new JMenu("File");
      JMenuItem item;
      FileMenuHandler fmh = new FileMenuHandler(this);
      item = new JMenuItem("Open");
      item.addActionListener(fmh);
      fileMenu.add(item);
      fileMenu.addSeparator();
      item = new JMenuItem("Quit");
      item.addActionListener(fmh);
      fileMenu.add(item);
      menuBar.add(fileMenu);
      
      //Creates the Convert menu, menu item, and initializes them with action listeners
      JMenu convertMenu = new JMenu("Convert");
      item = new JMenuItem("Roman to Arabic");
      ConvertMenuHandler cmh = new ConvertMenuHandler(this);
      item.addActionListener(cmh);
      convertMenu.add(item);
      menuBar.add(convertMenu);
      
   }

}