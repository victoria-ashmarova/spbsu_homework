import java.util.Scanner;

public class List {
    int size = 0;
    Element first = null;

    class Element{
        int value;
        Element next;
        Element(int value, Element next){
            this.value = value;
            this.next = next;
        }
    }

    enum Action{END, ADD, REMOVE, SEARCH, PRINT};

    private static void enterActions(){
        System.out.println("\nChoose an action:\n 1 - to add element\n 2 - to remove element\n 3 - to search value in list.\n 4 -  to print list.");
    }

    private static Action getAction(int number){
        switch (number){
            case 1:{
                return Action.ADD;
            }
            case 2:{
                return Action.REMOVE;
            }
            case 3:{
                return Action.SEARCH;
            }
            case 4:{
                return Action.PRINT;
            }
            default:{
                return Action.END;
            }
        }
    }

    private void addFirst(int value){
        Element newElement = new Element(value, first);
        first = newElement;
        size++;
    }

    public void add(int value){
        if (size < 1){
            addFirst(value);
            return;
        }

        if (isInList(value)){
            return;
        }

        if (value <= first.value){
            addFirst(value);
            return;
        }

        if (value > first.value && size == 1){
            Element toAdd = new Element(value, null);
            first.next = toAdd;
            size++;
            return;
        }

        Element prev = first;
        while (prev.next.next != null && prev.next.value < value){
            prev = prev.next;
        }
        if (prev.next.value < value){
            prev = prev.next;
        }

        Element toAdd = new Element(value, prev.next);
        prev.next = toAdd;
        size++;
    }

    public void remove(int value){
        if (!isInList(value) || size < 1)
            return;

        if (first.value == value){
            first = first.next;
            size--;
            return;
        }

        Element prev = first;
        while (prev.next.value != value){
            prev = prev.next;
        }
        prev.next = prev.next.next;
        size--;
    }

    public boolean isInList(int value){
        if (size < 1)
            return false;

        Element toCheck = first;
        while (toCheck != null && toCheck.value != value){
            toCheck = toCheck.next;
        }
        return toCheck != null;
    }

    public void print(){
        if (size < 1){
            System.out.println("\nList is empty.");
            return;
        }

        Element toPrint = first;
        System.out.println("Elements of list:");
        while (toPrint != null){
            System.out.println(toPrint.value + " ");
            toPrint = toPrint.next;
        }
    }

    public static void main(String args[]){
        System.out.println("This class gives you ability to work with sorted list.");
        enterActions();
        Scanner scan = new Scanner(System.in);
        List list = new List();
        int action = scan.nextInt();
        while (getAction(action) != Action.END){
            switch (getAction(action)){
                case ADD:{
                    System.out.println("Enter the value to add.");
                    int toAdd = scan.nextInt();
                    if (list.isInList(toAdd)) {
                       System.out.println("This value is in list.");
                    } else {
                        list.add(toAdd);
                    }
                break;
                }
                case REMOVE:{
                    System.out.println("Enter the value to remove.");
                    int toRemove = scan.nextInt();
                    if (!list.isInList(toRemove)){
                        System.out.println("This value is not in list.");
                    } else {
                        list.remove(toRemove);
                    }
                break;
                }
                case PRINT:{
                    list.print();
                break;
                }
                case SEARCH:{
                    System.out.println("Enter the value to search.");
                    int toSearch = scan.nextInt();
                    if (list.isInList(toSearch)){
                        System.out.println("Value is in list");
                    } else {
                        System.out.println("Value is not in list.");
                    }
                break;
                }
            }
            System.out.println("Enter the action again.");
            action = scan.nextInt();
        }
        System.out.println("You have finish work with list.");
    }
}
