import java.io.BufferedWriter;
import java.io.IOException;

public class QueueByHand {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    public QueueByHand(int size) {
        this.maxSize = size;
        this.queueArray = new int[maxSize];
        this.front = 0;
        this.rear = -1;
        this.nItems = 0;
    }

    public void insert(int value) {
        if (isFull()) {
            System.out.println("QueueByHand is full, cannot insert element: " + value);
            return;
        }
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queueArray[++rear] = value;
        nItems++;
    }

    public int remove() {
        if (isEmpty()) {
            System.out.println("QueueByHand is empty");
            return -1;
        }
        int temp = queueArray[front++];
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public int peekFront() {
        if (isEmpty()) {
            System.out.println("QueueByHand is empty");
            return -1;
        }
        return queueArray[front];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public void printQueue(BufferedWriter writer) throws IOException {
        if (isEmpty()) {
            writer.write("QueueByHand is empty.");
            writer.newLine();
            return;
        }


        int current = front;
        int count = 0;
        while (count < nItems) {
            writer.write(queueArray[current] + " ");
            current = (current + 1) % maxSize;
            count++;
        }
        writer.newLine();
    }

    public int getMaxSize() {
        return maxSize;
    }
    public boolean contains(int element) {
        int current = front;
        int count = 0;
        while (count < nItems) {
            if (queueArray[current] == element) {
                return true;
            }
            current = (current + 1) % maxSize;
            count++;
        }
        return false;
    }

    public int poll() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1; // Or throw an exception indicating empty queue
        }

        int temp = queueArray[front++]; // Get the element at the front and move the front pointer
        if (front == maxSize) {
            front = 0; // Wrap around if front reaches end of the array
        }
        nItems--;
        return temp;
    }

}
