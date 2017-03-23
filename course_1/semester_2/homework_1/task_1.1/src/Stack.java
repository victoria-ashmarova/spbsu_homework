import java.util.Scanner;

public class Stack {
    int size = 0;
    Element top = null;
    enum Action {END, PUSH, POP, GETINFORMATIONABOUTSIZE};

    class Element{
        int value;
        Element next;
        Element(int value, Element next){
            this.value = value;
            this.next = next;
        }
    }

    private static void enterActions(){
        System.out.println("Choose an action:\n 1 - to push element\n 2 - to pop element\n 3 - to get information about size\n else - to end work\n");
    }

    private static Action getAction(int number){
        switch (number){
            case 1:{
                return Action.PUSH;
            }
            case 2:{
                return Action.POP;
            }
            case 3:{
                return Action.GETINFORMATIONABOUTSIZE;
            }
            default:{
                return Action.END;
            }
        }
    }

    public void push(int value){
        Element newTop = new Element(value, this.top);
        top = newTop;
        size++;
    }

    public int pop(){
        if (size < 1)
            return -1;

        int toReturn = top.value;
        top = top.next;
        size--;
        return toReturn;
    }

    public void getInformationAboutSize()
    {
        if (size < 1) {
            System.out.println("stack is empty");
            return;
        }
        else{
            System.out.println("Size of stack is " + size);
        }
    }

    public static void main(String Args[]){
        System.out.println("This class give you appreciate to work with stack.");
        enterActions();
        Scanner scan = new Scanner(System.in);
        Stack stack = new Stack();
        int action = scan.nextInt();
        while (getAction(action) != Action.END)
        {
            switch (getAction(action)){
                case POP:{
                    int top = stack.pop();
                    if (top == -1) {
                        System.out.println("Stack is empty");
                    }else{
                        System.out.println("The top value was " + top);
                    }
                break;
                }
                case PUSH:{
                    System.out.println("Enter the value to push.");
                    int value = scan.nextInt();
                    stack.push(value);
                break;
                }
                case GETINFORMATIONABOUTSIZE:{
                    stack.getInformationAboutSize();
                break;
                }
            }
            System.out.println("Choose new action.");
            action = scan.nextInt();
        }
        System.out.println("You have finish work with stack.");
    }
}
