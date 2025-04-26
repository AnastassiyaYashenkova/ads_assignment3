public class MyHashTable <K, V>{

    private class HashNode <K, V> {
       private K key;
       private V value;
       private HashNode<K, V> next;

       public HashNode(K key, V value){
           this.key = key;
           this.value = value;
       }

       @Override
        public String toString(){
           return "{" + key + " " + value + "}";
       }
    }

    private HashNode<K, V> [] chainArray; // Array of buckets
    private int M = 11; // Initial number of buckets
    private double loadFactor = 0.75;
    private int size = 0; // Number of elements

    public int size(){ // Getting the number of elements
        return size;
    }

    public int getM(){
        return M;
    }

    private void increaseCapacity(){ // Increase the table's capacity
        M = M * 2;
        HashNode<K, V> []temp = chainArray;
        chainArray = new HashNode[M];
        for (int i = 0; i < temp.length; i++){
            if (temp[i] != null){
                HashNode<K, V> node = temp[i];
                while(node != null){
                    put(node.key, node.value);
                    node = node.next;
                }
            }
        }
    }

    public int countElements(int index){
        int count = 0;
        HashNode<K, V> temp = chainArray[index];
        while (temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    public MyHashTable(){ // Default constructor
        chainArray = new HashNode[M];
    }

    public MyHashTable(int initialCapacity){ // Constructor with initial capacity
        M = (int)(initialCapacity / loadFactor);
        chainArray = new HashNode[M];
    }

    private int hash(K key){ // Find the bucket by taking the positive part of the hash
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value){ // Add new element
        if ((double) size / M >= loadFactor)
            increaseCapacity();
        int index = hash(key);
        HashNode<K, V> temp = new HashNode<>(key, value);

        temp.next = chainArray[index];
        chainArray[index] = temp;
        size++;
    }

    public V get(K key){ // Find the element by its key
        int index = hash(key);
        HashNode<K, V> temp = chainArray[index];
        while (temp.next != null){
            if (temp.key.equals(key)){
                return (V) temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    public V remove(K key){ // Remove the element by its key
        int index = hash(key);
        HashNode<K, V> temp = chainArray[index];
        HashNode<K, V> prev = null;
        while(temp.next != null){
            if (temp.key.equals(key)){
                if (prev == null){
                    chainArray[index] = temp.next;
                }
                else {
                    prev.next = temp.next;
                }
                size--;
                return temp.value;
            }
            prev = temp;
            temp = temp.next;
        }
        return null;
    }

    public boolean containsK(K key){ // Checking whether K exists

        return get(key) != null;
    }

    public boolean containsValue(V value){ // Checking whether V exists
        for(int i = 0; i < M; i++){
            HashNode<K, V> temp = chainArray[i];
            while (temp != null){
                if (temp.value.equals(value)){
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    public K getKey(V value){  // Find the key of the element
        for (int i = 0; i < M; i++){
            HashNode<K, V> temp = chainArray[i];
            while (temp.next != null){
                if (temp.value.equals(value)){
                    return (K) temp.key;
                }
                temp = temp.next;
            }
        }
        return null;
    }
}
