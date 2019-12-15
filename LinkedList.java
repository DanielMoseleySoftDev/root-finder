package findrootofafunction;

public class LinkedList {

    protected LinkNode head;

    public LinkedList() {
        head = new LinkNode();
    }

    public void addLast(double x, double fx) {
        LinkNode tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
        tail.setNext(new LinkNode(x, fx, null));
    }

    public boolean isEmpty() {
        return head == null;
    }

    public  int getSize() {
        LinkNode temp;
        int size = 0;
        if (this.isEmpty()) {
            System.out.println("The list is empty");
        } else {
            temp = this.head;
            while (temp != null) {
                size++;
                temp = temp.getNext();
            }
        }
        return size;
    }

    public static void printList(LinkedList list)  {
        LinkNode temp;
        if (list.isEmpty()) {
            System.out.println("The List is Empty");
        }
        else {
            temp = list.head;
            while (temp != null) {
                System.out.println(temp.getX() + " " + temp.getFx());
                temp = temp.getNext();
                System.out.println();
            }
            System.out.println();
        }
    }
}