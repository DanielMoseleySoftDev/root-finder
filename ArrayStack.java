package findrootofafunction;

import java.util.EmptyStackException;

public class ArrayStack implements Stack{
    protected int capacity; //The size of the array
    public static final int CAPACITY = 5; //Default array capacity
    protected Object stackArray[];
    protected int top = -1; //-1 is an empty stack
    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int arrayCap) {
        capacity = arrayCap;
        stackArray = new Object[capacity];
    }

    @Override
    public int size() {
        return (top + 1);
    }

    @Override
    public boolean isEmpty() {
        return (top == -1);
    }

    @Override
    public Object top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray[top];
    }

    @Override
    public void push(Object element){
        if(size() == capacity) {
            doubleArraySize();
        }
    stackArray[++top] = element;
    }

    @Override
    public Object pop() throws EmptyStackException {
        Object element;
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        element = stackArray[top];
        stackArray[top--] = null;
        return element;
    }

    private void doubleArraySize() {
        Object [] newArray;
        System.out.println("Stack is full (max size was " + capacity + "). Increasing to " + (2 * capacity));
        //double variable capacity
        capacity = 2 * capacity;
        newArray = new Object[capacity];
        for(int i = 0; i < stackArray.length; i++)
            newArray[i] = stackArray[i];
        stackArray = newArray;
    }
}