import java.io.*;

public class Main {
    public static void main(String[] args) {
        String commandFile = "command.txt";
        String stackFile = "stack.txt";
        String queueFile = "queue.txt";

        StackByHand stack = readStackFromFile(stackFile);
        QueueByHand queue = readQueueFromFile(queueFile);

        try {
            BufferedReader br = new BufferedReader(new FileReader(commandFile));

            BufferedWriter stackOut = new BufferedWriter(new FileWriter("stackOut.txt "));
            BufferedWriter queueOut = new BufferedWriter(new FileWriter("queueOut.txt"));


            String line;
            while ((line = br.readLine()) != null) {
                String[] command = line.split("\\s+");
                char type = command[0].charAt(0);

                switch (type) {
                    case 'S':
                        Operations.performStackOperation(stack, stackOut, command);
                        updateStackFile(stack);
                        break;
                    case 'Q':
                        Operations.performQueueOperation(queue, queueOut, command);
                        updateQueueFile(queue);
                        break;
                    default:
                        break;
                }
            }

            br.close();
            stackOut.close();
            queueOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateQueueFile(QueueByHand queue) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("queue.txt"))) {
            queue.printQueue(writer);
        }
    }

    public static void updateStackFile(StackByHand stack) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("stack.txt"))) {
            stack.printStack(writer);
        }
    }
    private static StackByHand readStackFromFile(String fileName) {
        StackByHand stack = new StackByHand(100);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            String[] elements = line.split("\\s+");
            for (String element : elements) {
                stack.push(Integer.parseInt(element));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stack;
    }

    private static QueueByHand readQueueFromFile(String fileName) {
        QueueByHand queue = new QueueByHand(100);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            String[] elements = line.split("\\s+");
            for (String element : elements) {
                queue.insert(Integer.parseInt(element));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queue;
    }

}