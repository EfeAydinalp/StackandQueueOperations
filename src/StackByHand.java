import java.io.BufferedWriter;
import java.io.IOException;

public class StackByHand {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public StackByHand(int size) {
        this.maxSize = size;
        this.stackArray = new int[maxSize];
        this.top = -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("StackByHand is full, cannot push element: " + value);
            return;
        }
        stackArray[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("StackByHand is empty");
            return -1;
        }
        return stackArray[top--];
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("StackByHand is empty");
            return -1;
        }
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public void printStack(BufferedWriter writer) throws IOException {
        if (isEmpty()) {
            writer.write("StackByHand is empty.");
            writer.newLine();
            return;
        }


        for (int i = 0; i <= top; i++) {
            writer.write(stackArray[i] + " ");
        }
        writer.newLine();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public boolean contains(int element) {
        for (int i = 0; i <= top; i++) {
            if (stackArray[i] == element) {
                return true;
            }
        }
        return false;
    }



}
