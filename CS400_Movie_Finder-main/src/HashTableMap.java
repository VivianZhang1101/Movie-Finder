// --== CS400 File Header Information ==--
// Name: Vivian Zhang
// Email: wzhang556@wisc.edu
// Team: blue
// Group: CG
// TA: Xi
// Lecturer: Gary
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class writes a hash map to store ojects
 *
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private LinkedList<HashMapPair<KeyType, ValueType>>[] map;
  private HashMapPair pairs;
  private int capacity;
  private int size;
  private double loadCapacity;
  private final double MAX_CAPACITY = 0.85;

  /**
   * Initialize the HashTableMap. Default capacity is 10.
   */
  public HashTableMap() {
    capacity = 10;
    map = (LinkedList<HashMapPair<KeyType, ValueType>>[]) new LinkedList[capacity];

    size = 0;
  }

  /**
   * Initialize the HashTableMap.
   * 
   * @param capacity capacity
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    map = (LinkedList<HashMapPair<KeyType, ValueType>>[]) new LinkedList[capacity];
    size = 0;
  }

  /**
   * A private helper method to double the capacity and rehash the map
   */
  private void rehash() {
    LinkedList<HashMapPair<KeyType, ValueType>>[] oldmap = map;
    capacity = capacity * 2;
    // initialize the map
    map = new LinkedList[capacity];
    size = 0;
    // loop the values in the oldmap into the new map
    for (int i = 0; i < oldmap.length; i++) {
      if (oldmap[i] != null) {
        for (int j = 0; j < oldmap[i].size(); j++) {
          put((KeyType)oldmap[i].get(j).getKey(), (ValueType)oldmap[i].get(j).getValue());
        }
      }
    }
  }

  /**
   * store new values in your hash table according to key's hashcod
   * 
   * @param key   key
   * @param value key's value
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    if (key == null) {
      return false;
    }
    if (containsKey(key)) {
      return false;
    }
    int idx = Math.abs(key.hashCode() % (capacity));
    if (map[idx] == null) { // no keys in this index yet
      pairs = new HashMapPair(key, value);
      map[idx] = new LinkedList<>();
      map[idx].add(pairs);
      size++;
    } else { // this index already have keys
      pairs = new HashMapPair(key, value);
      map[idx].add(pairs);
      size++;
    }
    // check the capacity
    loadCapacity = (double) size / capacity;
    if (loadCapacity >= MAX_CAPACITY) {
      rehash();
    }
    return true;
  }



  /**
   * Get the value according to the giving key. Throw NoSuchElementException is the key does not
   * exist in the map.
   * 
   * @param key to get
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    if (key == null) {
      return null;
    }
    if (!containsKey(key)) {
      throw new NoSuchElementException();
    }
    int idx = Math.abs(key.hashCode() % (capacity));
    if (map[idx] == null) {
      return null;
    }
    for (int i = 0; i < map[idx].size(); i++) {
      if (map[idx].get(i).getKey().equals(key)) {
        return map[idx].get(i).getValue();
      }
    }
    return null;
  }

  /**
   * return size
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * return true if the key exists in the map, false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {
    if (key == null) {
      return false;
    }
    int idx = Math.abs(key.hashCode() % (capacity));
    if (map[idx] != null) {
      for (int i = 0; i < map[idx].size(); i++) {
        if (map[idx].get(i).getKey().equals(key)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * remove the key and value pair in the map according to the given key.
   * 
   * @param key key to be removed
   * @toRemove removed key
   */
  @Override
  public ValueType remove(KeyType key) {
    if (key == null) {
      return null;
    }
    if (!containsKey(key)) {
      return null;
    }
    int idx = Math.abs(key.hashCode() % (capacity));
    ValueType toRemove = null;
    for (int i = 0; i < map[idx].size(); i++) {
      if (map[idx].get(i).getKey().equals(key)) {
        toRemove = map[idx].get(i).getValue();
        map[idx].remove(i);
        size--;
      }
    }
    return toRemove;
  }

  /**
   * clear the map
   */
  @Override
  public void clear() {
    for (int i = 0; i < map.length; i++) {
      if (map[i] != null) {
        for (int j = 0; j < map[i].size(); j++) {
          remove((KeyType) map[i].get(j).getKey());
        }
      }
    }
  }

}
