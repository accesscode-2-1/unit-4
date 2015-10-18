package nyc.c4q.jrod;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MajorityElement {
  public static final int[] a1 = {3,3,4,2,4,4,2,4,4};
  public static final int[] a2 = {3,3,4,2,4,4,2,4};

  public static void main(String[] args) {
    System.out.printf("majority element in %s: %d\n", Arrays.toString(a1), findMajorityElement(a1));
    System.out.printf("majority element in %s: %d\n", Arrays.toString(a2), findMajorityElement(a2));
  }

  // O(N^2) algorithm
  // for a more complex solution which is O(N), see here:
  // http://www.geeksforgeeks.org/majority-element/
  private static int findMajorityElement(int[] a) {
    int N = a.length;
    for(int j = 0; j < N; j++) {
      int count = 0;
      for(int i = 0; i < N; i++) {
        if(a[i] == a[j]) count++;
        if(count > N / 2) return a[i];
      }
    }
    throw new NoSuchElementException();
  }
}
