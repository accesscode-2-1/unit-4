package nyc.c4q.jrod.list;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IntegerCounter {
  public static void main(String[] args) {
    findCounts(new int[]{1, 1, 1, 1, 2, 2, 3, 3, 5});
    findCounts(new int[]{1, 2, 2, 2, 2, 2, 3, 3, 3});
//    findCounts(Arrays.asList(1, 2, 3));
  }

  private static void findCounts(int[] a) {
    Map<Integer, Integer> counter = new HashMap<>();
    for (int e : a) {
      int count = 1;
      if(counter.containsKey(e)) {
        count = counter.get(e) + 1;
      }
      counter.put(e, count);
    }
    System.out.println(counter);
  }
//
//  private static void findCounts(Collection<Integer> a) {
//    Map<Integer, Integer> counter = new HashMap<>();
//    for (int e : a) {
//      int count = 1;
//      if(counter.containsKey(e)) {
//        count = counter.get(e) + 1;
//      }
//      counter.put(e, count);
//    }
//    System.out.println(counter);
//  }
}
