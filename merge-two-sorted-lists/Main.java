
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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode node1 = null;
        ListNode node2 = new ListNode(0);

        ListNode res = solution.mergeTwoLists(node1, node2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}