package project4;

import java.util.Comparator;

/**
 * Implements the Comparator class to tell the TreeMap how the keys are sorted
 * @author Yonghao Lin
 */
public class RomanComparator implements Comparator<RomanNumeral>{
   public int compare(RomanNumeral one, RomanNumeral two) {
      return one.compareTo(two);
   }
}
