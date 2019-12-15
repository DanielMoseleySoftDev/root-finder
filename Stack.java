package findrootofafunction;

import java.util.EmptyStackException;

public interface Stack {
    public int size(); // Get the size of the stack
    public boolean isEmpty(); // Check if the stack is Empty
    public Object top() throws EmptyStackException;// Get the top element of the stack, throws exception if empty
    public void  push(Object element);// Push an element onto the top of the stack
    public  Object pop() throws EmptyStackException;// Pops the top element off the stack, throws exception if empty
}

