public class MyStack<T extends Comparable<T>> {

    private MyArrayList<T> stack = new MyArrayList<>(); // MyArrayList is the best option, as it provides a faster access to the last (top) element, and there is no need for the strong connections between nodes from MyLinkedList

    public boolean isEmpty() { // Checks whether the stack is empty
        return stack.size() == 0;
    }

    public int size() { // Returns the number of elements in the stack
        return stack.size();
    }

    public T peek() { // Returns a reference to the element that is on the top of the stack
        return stack.getLast();
    }

    public void push(T item) { // Adds the element at the top of the stack
        stack.addLast(item);
    }

    public T pop() { // Outputs and deletes the topmost element of the stack
        T top = stack.getLast();
        stack.removeLast();
        return top;
    }

    public void clear() { // Clears the stack
        stack.clear();
    }
}
