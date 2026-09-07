class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] currentBest = new int[128];
        int bestLength = 0;
        int currentIndexBeginning = 0;
        int currentLength = 0;

        int indexOfElement;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            indexOfElement = currentBest[c];
                if (indexOfElement >= currentIndexBeginning) {
                    currentLength = i - (indexOfElement - 1) - 1;
                    currentIndexBeginning = Math.min(i, indexOfElement);
                }

            currentLength++;
            currentBest[c] = i + 1;

            bestLength = Math.max(bestLength, currentLength);

        }
        return bestLength;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    }
}