import java.util.ArrayDeque;
import java.util.List;

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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
    }

    public ListNode findHalf(ListNode head) {
        ListNode fast = null;
        ListNode slow = head;

        if (slow == null) {
            return null;
        }

        fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    public void reorderList(ListNode head) {
        ListNode half = findHalf(head);
        ListNode end = null;
        if (half != null) {
            end = reverseList(half.next);
            half.next = null;
        }

        ListNode next = null;
        ListNode current = head;

        while (end != null) {
            next = current.next;
            current.next = end;
            current = current.next;

            current.next = next;
            current = current.next;

        }


    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();


        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);

        solution.reorderList(head);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}