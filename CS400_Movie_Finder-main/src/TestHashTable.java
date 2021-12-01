// --== CS400 File Header Information ==--
// Name: Vivian Zhang
// Email: wzhang556@wisc.edu
// Team: blue
// Group: CG
// TA: Xi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * This class test the correctness of HashTableMap's methods
 *
 */
public class TestHashTable {
  public static void main(String[] args) {
    System.out.println("test 1: " + test1());
    System.out.println("test 2: " + test2());
    System.out.println("test 3: " + test3());
    System.out.println("test 4: " + test4());
    System.out.println("test 5: " + test5());
  }

  /**
   * Test the correctness of put() and size() methods.
   * 
   * @return true is the methods functioned correctly, false otherwise.
   */
  public static boolean test1() {
    HashTableMap a = new HashTableMap();
    a.put(1, 2);
    a.put(3, 4);
    a.put(5, 6);
    a.put(7, 8);
    if (a.size() == 4) {
      return true;
    }
    return false;
  }

  /**
   * Test the correctness of get() methods.
   * 
   * @return true is the methods functioned correctly, false otherwise.
   */
  public static boolean test2() {
    HashTableMap a = new HashTableMap();
    a.put(1, 4);
    a.put(2, 5);
    a.put(3, 6);
    if (a.get(3).equals(6)) {
      return true;
    }
    return false;
  }

  /**
   * Test the remove() method.
   * 
   * @return true is the methods functioned correctly, false otherwise.
   */
  public static boolean test3() {
    HashTableMap a = new HashTableMap();
    a.put(1, 2);
    a.put(3, 4);
    a.put(5, 6);
    a.put(7, 8);
    a.clear();
    if (a.size() != 0) {
      return false;
    }
    try {
      a.get(1);
      a.get(3);
      a.get(5);
      a.get(7);
    } catch (NoSuchElementException e) {
      return true;
    }
    return true;
  }

  /**
   * Test the containsKey() method.
   * 
   * @return true is the methods functioned correctly, false otherwise.
   */
  public static boolean test4() {
    HashTableMap a = new HashTableMap();
    a.put(1, 2);
    a.put(3, 4);
    a.put(5, 6);
    a.put(7, 8);
    if (!a.containsKey(1)) {
      return false;
    }
    if (a.containsKey(8) != false) {
      return false;
    }
    return true;
  }

  /**
   * Test the function's hash collision and duplicate keys.
   * 
   * @return true is the methods functioned correctly, false otherwise.
   */
  public static boolean test5() {
    HashTableMap a = new HashTableMap(4);
    a.put(12, 2);
    a.put(13, 4);
    a.put(23, 6);
    a.put(1, 2);
    a.put(3, 4);
    a.put(5, 6);
    a.put(7, 9);
    a.put(17, 2);
    a.put(17, 8);
    a.put(23, 4);
    a.put(54, 6);
    a.put(71, 9);
    // if (a.size() == 10) {
    // return true;
    // }
    ArrayList<String> v = new ArrayList();
    ArrayList<String> v1 = new ArrayList();
    v1.add("v1");
    v1.add("v11");
    v.add("ni");
    v.add("ihao");
    v.add("ihao");
    a.put(99, v1);
    a.put(98, v);
    ArrayList<String> v2 = new ArrayList();
    v2.addAll(v);
    v2.addAll(v1);
    System.out.println(v2);
    System.out.println(v2.size());
    v2.remove("ni");
    System.out.println(v2);
    System.out.println(v2.size());
    List<String> newList = v2.stream().distinct().collect(Collectors.toList());
System.out.println(newList);
    // System.out.println(a.get(11));
    // ArrayList<Object> v1 = new ArrayList();
    // v1.add(a.get(11));
    // System.out.println(v1);
String s = "hi, wo shi ni\"mam,a\"djdjdj, ad\"";
String[] d = s.split("\"");
    for(int i =0;i<d.length;i++) {
      System.out.println(d[i]);
    }
    String w = d[d.length-1];
    System.out.println(w);
    String z = w.substring(w.length()-2);
    System.out.println(z);
    return false;
    
  }
}
