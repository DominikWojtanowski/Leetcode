import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> pq = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k - 1; i++) {
            while (!pq.isEmpty() && nums[pq.peekLast()] < nums[i]) {
                pq.pollLast();
            }
            pq.add(i);
        }

        for (int i = 0; i <= nums.length - k; i++) {
            while (!pq.isEmpty() && nums[pq.peekLast()] < nums[i + k - 1]) {
                pq.pollLast();
            }
            pq.add(i + k - 1);
            result[i] = nums[pq.peekFirst()];
            if (!pq.isEmpty() && pq.peekFirst() == i ) {
                pq.pollFirst();
            }
        }

        return result;
    }

}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3)));
    }
}