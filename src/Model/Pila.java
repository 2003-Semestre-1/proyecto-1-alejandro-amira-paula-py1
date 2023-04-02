/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.EmptyStackException;

/**
 *
 * @author aleja
 */
public class Pila {
    
    private int[] data;
    private int top;
    private final int size;

    public Pila() {
        size = 10;
        data = new int[size];
        top = -1;
    }

    public void push(int element) throws StackOverflowError {
        if (top == size - 1) {
            throw new StackOverflowError("Stack is full");
        }
        top++;
        data[top] = element;
    }

    public int pop() throws EmptyStackException {
        if (top == -1) {
            throw new EmptyStackException();
        }
        int element = data[top];
        top--;
        return element;
    }

    public int peek() throws EmptyStackException {
        if (top == -1) {
            throw new EmptyStackException();
        }
        return data[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
    
    
}
