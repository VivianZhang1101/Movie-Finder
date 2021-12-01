
public class HashMapPair<KeyType, ValueType> {
  private KeyType key;
  private ValueType value;

  public HashMapPair(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  public KeyType getKey() {
    return key;
  }

  public ValueType getValue() {
    return value;
  }
}
