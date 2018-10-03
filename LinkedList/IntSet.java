
/*
 * This class maintains a set of integers. 
 */
public class IntSet {

    Node head;
    int size;

//Constructor 
    public IntSet() {
        head = null;
    }

    /**
     * Method to insert a new node to the end of this list.
     *
     */
    public void insert(int data) {

        if (head == null) {

            head = new Node(data, null);
        } else if (head != null) {
            //inserts the node into the new sorted list
            insertAscending(data);
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            if (find(data) == true) {

                //System.out.println("No duplicates allowed");
            } else if (find(data) == false) {

                temp.next = new Node(data, null);
            }
        }
    }

    /**
     * Method to remove the the specified element from the Set
     */
    public void remove(int key) {
        //if the key exists this entire section of code executes if not then 
        //the else if statement below tells the user that the key isn't present
        //in the set
        if (find(key) == true) {

            if (head.data == key) {
                head = head.next;
                return;
            }

            Node cur = head;
            Node prev = null;
            //while the current node isn't null and the current node's data isn't
            //the key continue crawling through the list
            while ((cur != null) && (cur.data != key)) {
                prev = cur;
                cur = cur.next;
            }
            //exception handling for when the list runs out
            if (cur == null) {
                throw new RuntimeException("cannot delete");
            }

            prev.next = cur.next;
        } else if (find(key) == false) {
            System.out.println("The Key doesn't exist");
        }
    }

    /*
    Method to print the entire Set 
     */
    public void print() {
        System.out.println("Printing Set");

        Node temp = head;

        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;

        }
    }

    /*
    Method to deteremine if a key is already present in a set or not
     */
    public boolean find(int key) {
        Node curr = head;

        boolean sentinal = false;

        while (curr != null) {

            if (curr.data == key) {
                sentinal = true;

            }

            curr = curr.next;
        }

        return sentinal;
    }

    /*
        Helper method that generates a linked list that will allow you print
        the set in order
     */
    public Node insertAscending(int data) {
        Node node = new Node(data, null);
        node.data = data;

        if (head == null) {
            head = node;
            return node;

        } //If the current node data is less than the head data then the next node
        //then node and head switch places
        else if (node.data < head.data) {

            node.next = head;
            head = node;
            return node;

        }

        Node temp = head;

        boolean added = false;

        while (temp.next != null) {
            //if the next nodes data is greater than the data being passed in,
            //then the next node is swapped with a node that takes its place
            //thus keeping the order
            if (temp.next.data > data) {
                node.next = temp.next;
                temp.next = node;
                added = true;
                break;
            }
            //next node
            temp = temp.next;
        }

        return node;
    }

}
