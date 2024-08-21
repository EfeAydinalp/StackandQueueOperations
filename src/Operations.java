import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class Operations {
    public static void performStackOperation(StackByHand stack, BufferedWriter stackOut, String[] command) throws IOException {
        if (command[1].equals("removeGreater")) {
            int k = Integer.parseInt(command[2]);
            StackByHand tempStack = new StackByHand(stack.getMaxSize()); // Create a temporary stack

            // Transfer elements less than or equal to k to the tempStack
            while (!stack.isEmpty()) {
                int value = stack.pop();
                if (value <= k) {
                    tempStack.push(value);
                }
            }

            // Transfer elements back to the original stack
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }

            // Write to the output file
            stackOut.write("After removeGreater " + k + ":\n");
            stack.printStack(stackOut);
            //stackOut.write("\n");

        } else if (command[1].equals("calculateDistance")) {
            int distanceSum = calculateStackDistance(stack);
            stackOut.write("After calculateDistance:" + "\n");
            stackOut.write("Total distance=" + distanceSum + "\n");
            // Write the distance sum to the output file
        }

        else if (command[1].equals("addOrRemove")) {
            int k = Integer.parseInt(command[2]);

            if (k < 0) {
                k = Math.abs(k);
                int size = stack.getMaxSize();

                StackByHand tempStack = new StackByHand(size);

                while (!stack.isEmpty()){
                    tempStack.push(stack.pop());
                }

                while (k>0){
                    tempStack.pop();
                    k--;
                }
                while (!tempStack.isEmpty()){
                    stack.push(tempStack.pop());
                }

            } else {
                // Add new random elements between 0 and 50
                Random random = new Random();
                while (k > 0) {
                    int newElement = random.nextInt(51); // Generate a random number between 0 and 50
                    stack.push(newElement);
                    k--;
                }
            }

            stackOut.write("After addOrRemove " + command[2] + ":\n");
            stack.printStack(stackOut);
            //stackOut.write("\n");
        }

        else if (command[1].equals("reverse")) {
            int k = Integer.parseInt(command[2]);
            if (k > stack.getMaxSize()) {
                // Invalid input for k
                stackOut.write("Invalid value of k for reverse operation.");
                stackOut.newLine();
            } else {
                StackByHand tempStack = new StackByHand(stack.getMaxSize());
                StackByHand tempStack2 = new StackByHand(stack.getMaxSize());
                StackByHand reverseStack = new StackByHand(stack.getMaxSize());

                int count = 0;

                // Pop the first k elements and store them in a temporary stack
                while (!stack.isEmpty()) {
                    reverseStack.push(stack.pop());

                }
                while (count < k && !reverseStack.isEmpty()) {
                    tempStack.push(reverseStack.pop());
                    count++;
                }


                // Push the elements from the temporary stack to a reverse stack
                while (!tempStack.isEmpty()) {
                    stack.push(tempStack.pop());
                }

                // Push back the reversed elements to the original stack
                while (!reverseStack.isEmpty()) {
                    stack.push(reverseStack.pop());
                }

                stackOut.write("After reverse " + command[2] + ":\n");
                stack.printStack(stackOut);

            }
        }

        else if (command[1].equals("sortElements")) {
            StackByHand tmpStack=sortstack(stack);
            while (!stack.isEmpty()){
                stack.pop();
            }

            while (!tmpStack.isEmpty()){
                stack.push(tmpStack.pop());

            }


            stackOut.write("After sortElements:" + "\n");
            stack.printStack(stackOut);
            stackOut.newLine();
        }
        else if (command[1].equals("distinctElements") ) {
            int count = countDistinctElements(stack);
            stackOut.write("After distinctElements:" + "\n");
            stackOut.write("Number of distinct elements in stack: " + count + "\n");
            stack.printStack(stackOut);
            stackOut.newLine();
        }

    }





//--------------------------------------------------------------------------------------------------
    public static void performQueueOperation(QueueByHand queue, BufferedWriter queueOut, String[] command) throws IOException {
        if (command[1].equals("removeGreater")) {
            int k = Integer.parseInt(command[2]);
            QueueByHand tempQueue = new QueueByHand(queue.getMaxSize()); // Create a temporary queue

            // Transfer elements less than or equal to k to the tempQueue
            while (!queue.isEmpty()) {
                int value = queue.remove();
                if (value <= k) {
                    tempQueue.insert(value);
                }
            }

            // Transfer elements back to the original queue
            while (!tempQueue.isEmpty()) {
                queue.insert(tempQueue.remove());
            }

            // Write to the output file
            queueOut.write("After removeGreater " + k + ":\n");
            queue.printQueue(queueOut);
            //queueOut.write("\n");

        } else if (command[1].equals("calculateDistance")) {
            int distanceSum = calculateQueueDistance(queue);
            queueOut.write("After calculateDistance:" + "\n");
            queueOut.write("Total distance=" + distanceSum + "\n");
            // Write the distance sum to the output file
        }

        else if (command[1].equals("addOrRemove")) {
            int k = Integer.parseInt(command[2]);

            if (k < 0) {
                k = Math.abs(k);
                while (k > 0 && !queue.isEmpty()) {
                    queue.remove();
                    k--;
                }
            } else {
                // Add new random elements between 0 and 50
                Random random = new Random();
                while (k > 0) {
                    int newElement = random.nextInt(51); // Generate a random number between 0 and 50
                    queue.insert(newElement);
                    k--;
                }
            }

            queueOut.write("After addOrRemove " + command[2] + ":\n");
            queue.printQueue(queueOut);
            //queueOut.write("\n");
        }
        else if (command[1].equals("reverse")) {
            int k = Integer.parseInt(command[2]);


                if (k > queue.getMaxSize()) {
                    // Invalid input for k
                    queueOut.write("Invalid value of k for reverse operation.");
                    queueOut.newLine();
                } else {
                    StackByHand tempStack = new StackByHand(100);
                    //QueueByHand tempQueue = new QueueByHand(queue.getMaxSize());

                    for (int i = 0; i < k; i++) {
                        tempStack.push(queue.peekFront());
                        queue.remove();
                    }
                    while (!tempStack.isEmpty()) {
                        queue.insert(tempStack.peek());
                        tempStack.pop();
                    }
                    for (int i = 0; i < queue.getMaxSize() - k; i++) {
                        queue.insert(queue.peekFront());
                        queue.remove();
                    }


                    queueOut.write("After reverse " + command[2] + ":\n");
                    queue.printQueue(queueOut);

                }


        }

        else if (command[1].equals("sortElements")) {
            QueueByHand tempQueue = new QueueByHand(100);


            queueOut.write("After sortElements: " + "\n");
            queue.printQueue(queueOut);
            queueOut.newLine();
        }

        else if (command[1].equals("distinctElements")) {
            int count = countDistinctElements(queue);

            queueOut.write("After distinctElements:" + "\n");
            queueOut.write("Total distinct element=" + count + "\n");

        }



    }



    //--------------------------------------------------------------------------------------------------
    public static int calculateStackDistance(StackByHand stack) {
        int distanceSum = 0;
        int elementsCount = 0;

        StackByHand tempStack = new StackByHand(stack.getMaxSize());

        // Transfer elements to a tempStack while counting the elements
        while (!stack.isEmpty()) {
            int element = stack.pop();
            tempStack.push(element);
            elementsCount++;
        }

        int[] elements = new int[elementsCount];
        int index = 0;

        // Copy elements to an array for easier access
        while (!tempStack.isEmpty()) {
            int element = tempStack.pop();
            elements[index++] = element;
        }

        // Calculate distances and sum them up
        for (int i = 0; i < elements.length; i++) {
            for (int j = i + 1; j < elements.length; j++) {
                distanceSum += Math.abs(elements[i] - elements[j]);
            }
        }

        // Restore elements back to the original stack
        for (int element : elements) {
            stack.push(element);
        }

        return distanceSum;
    }

    public static int calculateQueueDistance(QueueByHand queue) {
        int distanceSum = 0;
        int elementsCount = 0;

        QueueByHand tempQueue = new QueueByHand(queue.getMaxSize());

        // Transfer elements to a tempQueue while counting the elements
        while (!queue.isEmpty()) {
            int element = queue.remove();
            tempQueue.insert(element);
            elementsCount++;
        }

        int[] elements = new int[elementsCount];
        int index = 0;

        // Copy elements to an array for easier access
        while (!tempQueue.isEmpty()) {
            int element = tempQueue.remove();
            elements[index++] = element;
        }

        // Calculate distances and sum them up
        for (int i = 0; i < elements.length; i++) {
            for (int j = i + 1; j < elements.length; j++) {
                distanceSum += Math.abs(elements[i] - elements[j]);
            }
        }

        // Restore elements back to the original queue
        for (int element : elements) {
            queue.insert(element);
        }

        return distanceSum;
    }

    public static int countDistinctElements(StackByHand stack) {
        StackByHand tempStack = new StackByHand(stack.getMaxSize());
        int count = 0;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!tempStack.contains(current)) {
                count++;
                tempStack.push(current);
            }
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        return count;
    }

    public static int countDistinctElements(QueueByHand queue) {
        QueueByHand tempQueue = new QueueByHand(queue.getMaxSize());
        int count = 0;

        while (!queue.isEmpty()) {
            int current = queue.remove();
            if (!tempQueue.contains(current)) {
                count++;
                tempQueue.insert(current);
            }
        }

        while (!tempQueue.isEmpty()) {
            queue.insert(tempQueue.peekFront());
            tempQueue.remove();
        }

        return count;
    }
    public static StackByHand sortstack(StackByHand stack) {

        StackByHand tmpStack = new StackByHand(100);
        while(!stack.isEmpty())
        {
            // pop out the first element
            int tmp = stack.pop();

            // while temporary stack is not empty and
            // top of stack is lesser than temp
            while(!tmpStack.isEmpty() && tmpStack.peek()
                    < tmp)
            {
                // pop from temporary stack and
                // push it to the input stack
                stack.push(tmpStack.pop());
            }

            // push temp in temporary of stack
            tmpStack.push(tmp);
        }
        return tmpStack;
    }


}
