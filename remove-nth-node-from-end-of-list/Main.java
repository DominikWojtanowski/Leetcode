import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}



class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int listSize = 0;
        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;
        while (current != null) {
            listSize++;
            current = current.next;
        }

        int neededToFind = listSize - n;
        int currentCount = 0;
        current = head;
        while (currentCount != neededToFind) {
            prev = current;
            current = current.next;
            next = current.next;
            currentCount++;
        }

        if (listSize != 0) {
            if (prev == null) {
                return current.next;
            }
            prev.next = next;
        }

        return head;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();


        // Lista 1: 1 -> 4 -> 5
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));


        ListNode head = solution.removeNthFromEnd(list1,5);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}