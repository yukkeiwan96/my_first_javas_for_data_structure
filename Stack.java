public class Stack {

    String[] items;
    int   maxItems;
    int   top;

    /**
     *  Creates a new stack of Integers
     */
    public Stack(int maxItems) {
        items = new String[maxItems];
        top   = -1;
    }
    
    /**
     *  See if the stack is empty.
     */
     public boolean isEmpty() {
        if (top < 0)
            return true;
        else
            return false;
     }
     
    /**
     *  See if the stack is full.
     */
    public boolean isFull() {
        if (top == 4)
            return true;
        else
            return false;
    }

    
    /**
     *  Pops the top item off the stack and moves the top to the next item down.
     *  return the top item from the stack.
     */
    public String pop() {
        String StringToReturn;
        StringToReturn = items[top];
        top--;
        return StringToReturn;
    }
    
    /**
     *  Pushes an person onto the stack.
     */
    public void push(String person) {
        top++;
        items[top] = person;
        return;
    }

    public String peek(){
       String StringToReturn;
       StringToReturn = items[top];
       return StringToReturn;
    }
    
}
