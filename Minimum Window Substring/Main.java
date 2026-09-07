class Solution {
    public String minWindow(String s, String t) {
        int bestBeginning = 0;
        int bestEnd = Integer.MAX_VALUE;

        int[] sourceArray = new int[58];
        int sourceUniqueElements = 0;

        int[] temporaryArray = new int[58];
        int temporaryUniqueElements = 0;
        int temporaryBeginning = 0;

        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'A';
            sourceArray[index]++;
            if (sourceArray[index] == 1) {
                sourceUniqueElements++;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'A';
            temporaryArray[index]++;
            if (temporaryArray[index] == sourceArray[index]) {
                temporaryUniqueElements++;
                int bestLength = bestEnd - bestBeginning;
                int temporaryLength = i - temporaryBeginning;
                while (temporaryUniqueElements == sourceUniqueElements && temporaryBeginning <= i) {
                    index = s.charAt(temporaryBeginning) - 'A';
                    if (bestLength > (temporaryLength)) {
                        bestLength = temporaryLength;
                        bestBeginning = temporaryBeginning;
                        bestEnd = i + 1;
                    }
                    temporaryLength--;
                    temporaryBeginning++;
                    temporaryArray[index]--;
                    if (sourceArray[index] != 0 && temporaryArray[index] < sourceArray[index]) {
                        temporaryUniqueElements--;
                    }

                }
            }
        }

        if (bestEnd == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(bestBeginning,bestEnd);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minWindow("cgklivwehljxrdzpfdqsapogwvjtvbzahjnsejwnuhmomlfsrvmrnczjzjevkdvroiluthhpqtffhlzyglrvorgnalk", "mqfff"));
    }
}