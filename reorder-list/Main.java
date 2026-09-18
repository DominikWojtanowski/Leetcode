import java.util.ArrayDeque;

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
    public void reorderList(ListNode head) {
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        ListNode current = head;

        while (current != null) {
            stack.add(current);
            current = current.next;
        }

        current = head;

        if (!stack.isEmpty()) {
            stack.pollFirst();
        }

        while (!stack.isEmpty()) {
            current.next = stack.pollLast();
            current = current.next;
            if (!stack.isEmpty()) {
                current.next = stack.pollFirst();
                current = current.next;
            }
        }

        current.next = null;

    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();


        ListNode head = new ListNode(1);

        solution.reorderList(head);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}