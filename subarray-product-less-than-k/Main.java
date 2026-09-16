class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int count = 0;
        int currentProduct = 1;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            currentProduct *= nums[i];
            while (currentProduct >= k) {
                currentProduct /= nums[left];
                left++;
            }
            count += (i - left + 1);
        }

        return count;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSubarrayProductLessThanK(new int[]{1, 2},2));
    }
}