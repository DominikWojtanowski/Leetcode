class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int currentFreq = 0;
        int maxFreq = 0;
        int left = 0;
        int sSize = s.length();
        for (int i = 0; i < sSize; i++) {
            int index = s.charAt(i) - 'A';
            freq[index]++;
            currentFreq = Math.max(currentFreq, freq[index]);
            while ((i + 1 - left) - currentFreq > k) {
                int leftIndex = s.charAt(left) - 'A';
                freq[leftIndex]--;
                if (i + 1 == sSize) {
                    return maxFreq;
                }
                int rightIndex = s.charAt(i + 1) - 'A';
                freq[rightIndex]++;
                currentFreq = Math.max(currentFreq, freq[rightIndex]);
                left++;
                i++;
            }
            currentFreq = Math.max(currentFreq, freq[index]);
            maxFreq = Math.max(maxFreq, (i + 1) - left);
        }

        return maxFreq;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.characterReplacement("AABABBA",1));
    }
}