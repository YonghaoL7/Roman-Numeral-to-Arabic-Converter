package project4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * This class handles the menu item events present in the File menu made in the RomanNumeralGUI class
 * @author Yonghao
 *
 */
public class FileMenuHandler implements ActionListener {
   JFrame jframe;
   TextArea roman = new TextArea();
   TextArea arabic = new TextArea();
   
   /**
    * Creates a FileMenuHandler object
    * @param jf  the JFrame for the GUI
    */
   public FileMenuHandler (JFrame jf) {
      jframe = jf;
   }
   
   /**
    * Handles the events for when a menu item in the File menu is selected
    * If the menu item "Open" is selected, the user is prompted to select a file
    * If the menu item "Quit" is selected, it exits the program
    * @param event  the event that occurred 
    */
   public void actionPerformed(ActionEvent event) {
      String  menuName;
      menuName = event.getActionCommand();
      if (menuName.equals("Open"))
         openFile( ); //Prompts file
      else if (menuName.equals("Quit"))
         System.exit(0); //Exits program
   } //actionPerformed
   
   /**
    * If the user chooses a file, the file is opened
    * Otherwise, a dialog box appears stating that the process was canceled 
    * @author - Kenneth Lord CSCI 212
    * 
    */
   private void openFile() {
       JFileChooser chooser;
       int          status;
       chooser = new JFileChooser( );
       status = chooser.showOpenDialog(null);
       if (status == JFileChooser.APPROVE_OPTION) 
          readSource(chooser.getSelectedFile());
       else 
          JOptionPane.showMessageDialog(null, "Open File dialog canceled");
    } //openFile
  
   /**
    * Creates and sets the layout for the GUI as two columns: roman and arabic
    * The Roman numeral strings and their Arabic values are displayed in their respective text areas
    * @param chosenFile  the file that the user had chosen
    */
   private void readSource(File chosenFile) {
      //Creates the grid layout for the GUI
      String chosenFileName = chosenFile.getAbsolutePath();
      TextFileInput inFile = new TextFileInput(chosenFileName);
      Container myContentPane = jframe.getContentPane();
      jframe.setLayout(new GridLayout(1, 2));
      //Clears the TextAreas for future files being opened
      roman.setText("");
      arabic.setText("");
      //Adds the TextAreas and their titles
      myContentPane.add(roman);
      myContentPane.add(arabic);
      roman.append("Roman Numerals\n");
      arabic.append("Arabic Values\n");
      
      //Reads the text file
      String line = inFile.readLine();
      String romanNumString;
      TreeMap<RomanNumeral, Integer> treeMap = new TreeMap<>(new RomanComparator());
      //Goes through the entire text file and reads each Roman numeral string 
      while(line != null) {
         StringTokenizer token = new StringTokenizer(line, ",");
         while(token.hasMoreTokens()) {
             romanNumString = token.nextToken();
             try {
                RomanNumeral romanNumeral = new RomanNumeral(romanNumString); //Tests whether there is an IllegalRomanNumeralException
                treeMap.put(romanNumeral, romanNumeral.getArabicVal()); //Enters the key and values into a tree map to be sorted
             }
             catch(IllegalRomanNumeralException illegalRomanNumeral) {
                JOptionPane.showMessageDialog(null, "Illegal Roman Numeral"); 
                throw new IllegalRomanNumeralException("Illegal Roman Numeral: " + romanNumString); //Throws an exception and prints the invalid Roman numeral string in the console
            }
         }
         line = inFile.readLine();
     }
     Set set = treeMap.entrySet();
     Iterator i = set.iterator();
     Map.Entry<RomanNumeral, Integer> map;
     //Iterates through the sorted tree map and appends each key and their corresponding values onto their respective columns in the gui
     try {
        while(i.hasNext()) {
           map = (Map.Entry)i.next();
           roman.append(map.getKey() + "\n");
           arabic.append(map.getValue() + "\n");
        }
        jframe.setVisible(true); 
     }
     catch(IllegalRomanNumeralException irne) {
        JOptionPane.showMessageDialog(null, "Illegal Roman Numeral"); 
        System.out.println("Illegal Roman Numeral");
     }
   }
}