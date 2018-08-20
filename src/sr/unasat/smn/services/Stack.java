package sr.unasat.smn.services;

public class Stack {

    private final int SIZE = 20;
    private int[] stack;
    private int top;

    public Stack() {
        stack = new int[SIZE];
        top = -1;
    }

    public void push(int j) {
        stack[++top] = j;
    }

    public int pop()
    {
        return stack[top--];
    }

    public int peek() {
        return stack[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }
}
