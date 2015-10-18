package nyc.c4q.jrod;

public class BitstringMethods {

  public static final int NUM_JAVA_INT_BITS = 32;

  // suggestion: run this code first, before analyzing
  public static void main(String[] args) {
    testAllBitstringMethods(29);
    testAllBitstringMethods(27);
    testAllBitstringMethods(5);
    testAllBitstringMethods(6);
  }

  private static void testAllBitstringMethods(int number) {
    System.out.println("number: " + number);
    System.out.println("our bitstring: " + convertToBitstring(number));
    System.out.println("java's bitstring: " + Integer.toBinaryString(number)); // test
    System.out.println("our 1s count: " + countOneBits(number));
    System.out.println("java's 1s count: " + Integer.bitCount(number)); // test
    System.out.println("is palindrome (1st approach)?: " + isBinaryPalindrome1(number));
    System.out.println("is palindrome (2nd approach)?: " + isBinaryPalindrome2(number));
    System.out.println();
  }

  // 29 is ...011101
  private static String convertToBitstring(int number) {
    char[] bitsAsChars = new char[NUM_JAVA_INT_BITS];
    int writeIndex = NUM_JAVA_INT_BITS - 1; // writing from right to left

    while (number != 0) {
      // get the LSB
      // AND'ing with 1 turns all bits except LSB to 0
      int lsb = number & 0x01;

      // convert bit to char
      if (lsb == 1)
        bitsAsChars[writeIndex] = '1';
      else
        bitsAsChars[writeIndex] = '0';

      // move left
      writeIndex--;

      // logical shift right
      // don't use arithmetic here or negative numbers will cause
      // an ArrayIndexOutOfBoundsException (why?)
      number >>>= 1;
    }

    // convert char array to string
    // only use the range of chars that was written (why?)
    // writeIndex + 1 is the position of the last character written (why?)
    // NUM_JAVA_INT_BITS - writeIndex - 1 is the numbers of characters written (why?)
    String bitstring = new String(bitsAsChars, writeIndex + 1, NUM_JAVA_INT_BITS - writeIndex - 1);

    return bitstring;
  }

  private static int countOneBits(int number) {
    int count = 0;
    while (number != 0) {
      int lsb = number & 0x01;
      if (lsb == 1) {
        count++;
      }
      number >>= 1;
    }
    return count;
  }

  // two ways to solve
  // easiest is to reuse the bitstring method
  // then use the palindrome algorithm discussed in class (and implemented below)
  private static boolean isBinaryPalindrome1(int number) {
    String bitstring = convertToBitstring(number);
    return isPalindrome(bitstring);
  }

  // another way, more complicated but more efficient since it avoids the string
  // suggestion: draw it out on paper/whiteboard!
  private static boolean isBinaryPalindrome2(int number) {
    if(number == 0) return true;

    // this is a 1 in the leftmost bit location
    // if this were a byte, then the mask would 10000000
    // since this is a int, the mask is 10000000 0000000 0000000 0000000
    int leftMask = 1 << (NUM_JAVA_INT_BITS - 1);
    //System.out.println("left mask: " + convertToBitstring(leftMask));

    // perform AND on number and mask, shifting the mask
    // until we find the position of the leftmost 1 in number
    while((leftMask & number) == 0) {
      leftMask >>>= 1;
    }
    // now this mask represents the position of the msb bit in number
    //System.out.println("left mask: " + convertToBitstring(leftMask));

    // this mask represents the position of the lsb bit in number
    int rightMask = 1;

    // now that we have set up our positions in the bitstring
    // we can perform the actual palindrome check

    // remember that leftMask is a larger number than rightMask
    // also note the similarity to the while loop in isPalindrome
    while(leftMask > rightMask) {
      // turn off all bits except the bit specified by leftMask
      int leftBit = number & leftMask;
      // turn off all bits except the bit specified by rightMask
      int rightBit = number & rightMask;

      // both bits must be either 0 or non-zero
      // else there is a mismatch => not a palindrome
      if(leftBit == 0 && rightBit != 0) return false;
      if(leftBit != 0 && rightBit == 0) return false;

      // move the leftmost position to the right
      leftMask >>>= 1;
      // move the rightmost position to the left
      rightMask <<= 1;
      //System.out.println("left mask: " + convertToBitstring(leftMask));
      //System.out.println("right mask: " + convertToBitstring(rightMask));
    }

    return true;
  }

  private static boolean isPalindrome(String s) {
    // left and right indeces
    int l = 0;
    int r = s.length() - 1;

    // while the indeces haven't passed each other
    while(l < r) {
      // if there is a mismatch, not a palindrome!
      if (s.charAt(l) != s.charAt(r)) {
        return false;
      }

      // move leftmost index right
      l++;
      // move rightmost index left
      r--;
    }

    // we didn't find a mismatch, so this is a palindrome!
    return true;
  }
}