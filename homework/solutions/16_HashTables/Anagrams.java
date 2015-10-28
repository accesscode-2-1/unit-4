package nyc.c4q.jrod.current.hashtables;

import java.util.HashMap;
import java.util.Map;

public class Anagrams {
  public static void main(String[] args) {
    String s1 = "eleven plus two?";
    String s2 = "TWELVE PLUS ONE!!";
    System.out.println("is anagram?: " + areAnagrams(s1, s2));

    s1 = "eleven plus two?";
    s2 = "TWELVE PLUS TWO!!";
    System.out.println("is anagram?: " + areAnagrams(s1, s2));
  }

  private static boolean areAnagrams(String s1, String s2) {
    // we could use two maps and compare, but it's more
    // memory efficient to use one map and
    // "count up" for s1 followed by "count down" for s2
    Map<Character, Integer> characterCounter =
            new HashMap<>(Math.max(s1.length(), s2.length()));

    for(char c: s1.toCharArray()) {
      // ignore non-letters
      if(!Character.isAlphabetic(c)) continue;

      // make lowercase to compare letters ignoring case
      c = Character.toLowerCase(c);

      Integer count = characterCounter.get(c);
      if(count == null) { // first time we've seen this char
        characterCounter.put(c, 1);
      }
      else {
        characterCounter.put(c, count + 1);
      }
    }

    // we now know how many of each letter exists in s1
    // next, we iterate over s2 and for each letter, we decrement
    // its count by 1.  one exception: we don't let a letter's
    // count go to 0.  why?  because then to prove the two strings
    // are anagrams, we would have to iterate over the map checking
    // that each key has a value of 0.  it's more efficient to
    // remove the key when the count would be 0.  this means that
    // IF the two strings are anagrams, the map must be empty at
    // the end of this loop.

    for(char c: s2.toCharArray()) {
      // ignore non-letters
      if(!Character.isAlphabetic(c)) continue;

      // make lowercase to compare letters ignoring case
      c = Character.toLowerCase(c);

      Integer count = characterCounter.get(c);
      if(count == null) {
        // this is weird, we found an extra char in s2
        return false;
      }
      else if(count == 1) {
        characterCounter.remove(c);
      }
      else {
        characterCounter.put(c, count - 1);
      }
    }

    return characterCounter.isEmpty();
  }
}
