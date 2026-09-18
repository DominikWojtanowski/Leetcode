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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode current = null;
        ListNode head = null;

        if (list1 == null && list2 == null) {
            return null;
        }

        if (list1 == null) {
            current = list2;
            list2 = list2.next;
        } else if (list2 == null) {
            current = list1;
            list1 = list1.next;
        } else {
            if (list1.val <= list2.val) {
                current = list1;
                list1 = list1.next;
            } else {
                current = list2;
                list2 = list2.next;
            }
        }

        head = current;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                current = current.next;
                list1 = list1.next;
            } else {
                current.next = list2;
                current = current.next;
                list2 = list2.next;
            }
        }

        while (list1 != null) {
            current.next = list1;
            current = current.next;
            list1 = list1.next;
        }

        while (list2 != null) {
            current.next = list2;
            current = current.next;
            list2 = list2.next;
        }

        return head;

    }
    public ListNode mergeKLists(ListNode[] lists) {
       int end = lists.length;
       int nextEnd = 0;
       int right;

       while (end > 1) {
           for (right = 0; right + 1 < end; right+=2) {
                ListNode merged = mergeTwoLists(lists[right], lists[right + 1]);
                lists[nextEnd] = merged;
                nextEnd++;
           }
           if (right < end) {
               lists[nextEnd] = lists[right];
               nextEnd++;
           }
           end = nextEnd;
           nextEnd = 0;
       }

       if (lists.length == 0) {
           return null;
       }
       return lists[0];
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();


        // Lista 1: 1 -> 4 -> 5
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));

// Lista 2: 1 -> 3 -> 4
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

// Lista 3: 2 -> 6
        ListNode list3 = new ListNode(2, new ListNode(6));

// Spakowanie do tablicy (jeśli funkcja przyjmuje ListNode[])
        ListNode[] lists = new ListNode[]{list1, list2, list3};

        ListNode head = solution.mergeKLists(lists);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}