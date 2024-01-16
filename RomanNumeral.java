package project4;

import java.util.HashMap;
/**
 * 
 * @author Yonghao
 * This class represents a Roman Numeral object that has the string and Arabic version of a Roman numeral. Contains methods to set the value of the string, get the value of the string and Arabic value, compare RomanNumerals and check if two RomanNumerals are equal
 *
 */
public class RomanNumeral {
   public String romanNumeral;
   public int arabicVal;
   
   /**
    * Constructor that makes a Roman Numeral object and assigns it the given string value and assigns the calculated arabic value of the string
    * @param romanString    string version of a Roman numeral
    */
   public RomanNumeral(String romanString) {
      romanNumeral = romanString;
      try { //Tests whether the Roman numeral string can be converted into an Arabic value
         arabicVal = valueOfString(romanString);
      }
      catch(IllegalRomanNumeralException illegalRomanNumeral) { //If the string cannot be converted, an exception is thrown
         arabicVal = 0;
         throw new IllegalRomanNumeralException("Illegal Roman Numeral " + romanString);
      }
   }
   
   /**
    * Returns the string value of the RomanNumeral
    * @return   string version of a Roman numeral
    */
   public String getRomanNumeral() {
      return romanNumeral;
   }
   
   /**
    * Returns the Arabic value of the RomanNumeral
    * @return   Arabic value of the Roman numeral
    */
   public int getArabicVal() {
      return arabicVal;
   }
   
   /**
    * Sets the string value of the RomanNumeral to given string
    * @param romanString    the new string version of a Roman numeral
    */
   public void setRomanNumeral(String romanString) {
      romanNumeral = romanString;
   }
   
   /**
    * Tests whether two objects are equal based on their Class. If they are both RomanNumeral objects, the Arabic values are compared to test if they're equal
    * @param    object that is being compared to
    * @return   returns true if the two objects are equal but returns false otherwise
    */
   public boolean equals(Object object) {
      if(object != null && this.getClass().equals(object.getClass())) {
         RomanNumeral obj = (RomanNumeral)object;
         if(this.getRomanNumeral().equals(obj.getRomanNumeral()) && this.getArabicVal() == obj.getArabicVal()) {
            return true;
         }
      }
      return false;
   }
   
   /**
    * Compares the Arabic values of two RomanNumeral objects
    * @param other  the other RomanNumeral object that is being compared to 
    * @return   1 if this RomanNumeral's Arabic value is greater than the other's, -1 if it's less, and 0 if they're equal
    */
   public int compareTo(RomanNumeral other) {
      if(this.getArabicVal() > other.getArabicVal()) {
         return 1;
      }
      else if(this.getArabicVal() < other.getArabicVal()) {
         return -1;
      }
      return 0;
   }
   
   /**
    * Returns a string version of the RomanNumeral object's string Roman numeral and Arabic value
    * @return   string that consists of both the string Roman numeral and Arabic value
    */
   public String toString() {
      return romanNumeral;
   }
   
   /**
    * Returns the Arabic value of a Roman numeral string
    * @param letter Roman numeral letter 
    * @return   Arabic value of the Roman numeral string
    */
   public static int valueOfString(String numeral) {
      //Adds pairs of keys and values of corresponding numerals to their arabic values in a hashmap
      HashMap<Character, Integer> map = new HashMap<Character, Integer>();
      map.put('I', 1);
      map.put('V', 5);
      map.put('X', 10);
      map.put('L', 50);
      map.put('C', 100);
      map.put('D', 500);
      map.put('M', 1000);
      try {
         //Goes through each letter of the string starting from the last character
         int length = numeral.length();
         int result = map.get(numeral.charAt(length - 1));
         for(int i = length - 2; i >= 0; i--) {
            //Checks whether the value should be added or subtracted from the total
            if(map.get(numeral.charAt(i)) >= map.get(numeral.charAt(i + 1))) { 
               result += map.get(numeral.charAt(i));
            }
            else {
               result -= map.get(numeral.charAt(i));
            }
         }
         return result;
      }
    //If the character or value isn't present then an IllegalRomanNumeralException is thrown
      catch(IllegalRomanNumeralException irne) { 
         throw new IllegalRomanNumeralException("Illegal Roman Numeral");
      }
      catch(NullPointerException e) {
         throw new IllegalRomanNumeralException("Illegal Roman Numeral");
      }
   }
}
