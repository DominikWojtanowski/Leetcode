class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int currentSum = 0;
        int minimumLength = Integer.MAX_VALUE;
        int left = 0;

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (currentSum >= target) {
                while (currentSum >= target) {
                    currentSum -= nums[left];
                    left++;
                }
                if (left - 1 >= 0) {
                    minimumLength = Math.min(minimumLength, (i + 1) - left + 1);
                }
            }
        }

        return minimumLength == Integer.MAX_VALUE ? 0 : minimumLength;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }
}