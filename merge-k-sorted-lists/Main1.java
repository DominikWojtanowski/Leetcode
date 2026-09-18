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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length == 0 ? 1 : lists.length, Comparator.comparingInt((ListNode list) -> list.val));

        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }

        if (pq.isEmpty()) {
            return null;
        }

        ListNode head = pq.poll();
        ListNode start = head;
        if (head.next != null) {
            pq.add(head.next);
        }

        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll();
            if (smallest.next != null) {
                pq.add(smallest.next);
            }
            head.next = smallest;
            head = head.next;
        }

        return start;
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