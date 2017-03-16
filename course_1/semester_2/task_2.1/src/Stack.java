import java.util.Scanner;

public class Stack {

    int size;
    Element top;

    enum Action {END, PUSH, POP, GETINF};

    static class Element{
        int value;
        Element next;
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
                return Action.GETINF;
            }
            default:{
                return Action.END;
            }
        }
    }

    public static Stack createStack()
    {
        Stack stack = new Stack();
        stack.top = null;
        stack.size = 0;
        return stack;
    }

    public static void push(Stack stack, int value){
        Element newTop = new Element();
        newTop.value = value;
        newTop.next = stack.top;
        stack.top = newTop;
        stack.size++;
    }

    public static int pop(Stack stack){
        if (stack.size < 1)
            return -1;

        int toReturn = stack.top.value;
        stack.top = stack.top.next;
        stack.size--;
        return toReturn;
    }

    public static void getInf(Stack stack)
    {
        if (stack.size < 1) {
            System.out.println("\nstack is empty");
            return;
        }
        else{
            System.out.println("\nSize of stack is " + stack.size);
        }
    }

    public static void main(String Args[]){
        System.out.println("This class give you appreciate to work with stack.");
        enterActions();
        Scanner scan = new Scanner(System.in);
        Stack stack = createStack();
        int action = scan.nextInt();
        while (getAction(action) != Action.END)
        {
            switch (getAction(action)){
                case POP:{
                    int top = pop(stack);
                    if (top == -1) {
                        System.out.println("Stack is empty");
                    }else{
                        System.out.println("\n The top value was " + top);
                    }
                break;
                }
                case PUSH:{
                    System.out.println("\nEnter the value to push.\n");
                    int value = scan.nextInt();
                    push(stack, value);
                break;
                }
                case GETINF:{
                    getInf(stack);
                break;
                }
            }
            System.out.println("\nChoose new action.");
            action = scan.nextInt();
        }
        System.out.println("\nYou have finish work with stack.\n");
    }
}
