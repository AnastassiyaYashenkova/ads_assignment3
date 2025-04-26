import java.util.Iterator;

public class MyArrayList<T extends Comparable<T>> implements MyList<T> {

    private Object[] arr; // The array which stores the elements of the MyArrayList
    private int length; // The number of elements in the list

    public MyArrayList(){ // Sets the initial capacity
        this(5);
    }

    public MyArrayList(int initialCapacity){ // An empty list with the initial capacity
        arr = new Object[initialCapacity];
    }

    public void increaseCapacity(){ // Increases the capacity of the list
        Object[] temp = new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++){
            temp[i] = arr[i];
        }
        arr = temp;
    }

    @Override
    public void add(T item) { // Inserts the element at the end of the list
        if (length >= arr.length){
            increaseCapacity();
        }
        arr[length++] = item;
    }

    @Override
    public void set(int index, T item) { // Replaces the element at the specified position with the new element
        if (index >= arr.length){
            increaseCapacity();
        }
        arr[index] = item;
    }

    @Override
    public void add(int index, T item) { // Inserts the element at the specified position in the list
        for (int i = length; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = item;
        length++;
    }

    @Override
    public void addFirst(T item) { // Inserts the element at the top of list
        if(arr.length == length){
            increaseCapacity();
        }

        for (int i = length; i > 0; i--) {
            arr[i] = arr[i - 1];
        }

        arr[0] = item;
        length++;
    }

    @Override
    public void addLast(T item) { // Inserts the element at the end of the list
        add(item);
    }

    @Override
    public T get(int index) { // Returns the element at the specified position in the list
        return (T) arr[index];
    }

    @Override
    public T getFirst() { // Returns the first element in the list
        return (T) arr[0];
    }

    @Override
    public T getLast() { // Returns the last element in the list
        return (T) arr[--length];
    }

    @Override
    public void remove(int index) { // Removes the element at the specified position
        for (int i = index; i < length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[--length] = null;
    }

    @Override
    public void removeFirst() { // Removes the first element
        remove(0);
    }

    @Override
    public void removeLast() { // Removes the last element
        remove(--length);
    }

    @Override
    public void sort() { // Sorts the elements in the list in ascending order
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length-1-i; j++){
                if (((Comparable)arr[j]).compareTo(arr[j+1]) > 0){
                    Object temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    @Override
    public int indexOf(T object) { // Returns the index of the first occurrence of the element in the list (if there is no such element, returns -1)
        for (int i = 0; i < length; i++) {
            if (arr[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T object) { // Returns the index of the last occurrence of the element in the list (if there is no such element, returns -1)
        for (int i = length - 1; i >= 0; i--) {
            if (arr[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(T object) { // Returns true if the list contains the given element
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() { // Returns an array containing all elements from the list in a sequence
        Object[] temp = new Object[length];
        System.arraycopy(arr, 0, temp, 0, length);
        return temp;
    }

    @Override
    public void clear() { // Removes all elements from the list
        for (int i = 0; i < length; i++) {
            arr[i] = null;
        }
        length = 0;

    }

    @Override
    public int size() { // Returns the number of elements in the list
        return length;
    }

    @Override
    public Iterator<T> iterator() { // Returns an iterator over the elements in the list in sequence
        return new MyIterator();
    }

    public class MyIterator implements Iterator<T>{ // Iterator class
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public T next() {
            return (T) arr[index++];
        }
    }
}