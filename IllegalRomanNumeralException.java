package project4;

/**
 * An exception for an Illegal Roman numeral
 * @author Yonghao
 *
 */
public class IllegalRomanNumeralException extends IllegalArgumentException{
   
   /**
    * Constructs an IllegalRomanNumeralException with the inputed message
    * @param message  the detail message of the exception
    */
   public IllegalRomanNumeralException(String message) {
      super(message);
   }

}
