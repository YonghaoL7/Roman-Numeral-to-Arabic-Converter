package project4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class handles the menu item events present in the Convert menu made in the RomanNumeralGUI class
 * @author Yonghao
 *
 */
public class ConvertMenuHandler implements ActionListener {
   JFrame jframe; 
   
   /**
    * Creates a ConvertMenuHandler object
    * @param jf  the JFrame for the GUI
    */
   public ConvertMenuHandler (JFrame jf) {
      jframe = jf;
   }
   
   /**
    * When the menu item is selected, the user is able to type in a Roman numeral string and the Arabic value of it is returned in a dialog box
    * If the entered Roman numeral string is invalid, an exception is thrown
    * @param event  the event that occurred
    */
   public void actionPerformed(ActionEvent event) {
      String  menuName;
      menuName = event.getActionCommand();
      if (menuName.equals("Roman to Arabic")) {
         String numeral = JOptionPane.showInputDialog(null, "Enter a Roman Numeral");
         try { 
            RomanNumeral roman = new RomanNumeral(numeral); //Tests whether there is an IllegalRomanNumeralException
            JOptionPane.showMessageDialog(null, RomanNumeral.valueOfString(numeral)); 
         }
         catch(IllegalRomanNumeralException e) {
            JOptionPane.showMessageDialog(null, "Illegal Roman Numeral: " + numeral);
            throw new IllegalRomanNumeralException("Illegal Roman Numeral: " + numeral); //Throws an exception and prints the invalid Roman numeral string in the console
         }
      }
         
   } //actionPerformed

}
