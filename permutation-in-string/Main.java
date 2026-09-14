class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] neededToFindFromArray = new int[26];
        int howManyPermutationParts = 0;

        int[] neededToFindInArray = new int[26];
        int howManyPermutationPartsFound = 0;
        
        for (char c: s1.toCharArray()) {
            int index = c - 'a';
            if (neededToFindFromArray[index] == 0) {
                howManyPermutationParts++;
            }
            neededToFindFromArray[index]++;
        }

        for (int i = 0; i < s1.length(); i++) {
            int index = s2.charAt(i) - 'a';
            if (neededToFindFromArray[index] != 0) {
                neededToFindInArray[index]++;
                if (neededToFindInArray[index] == neededToFindFromArray[index]) {
                    howManyPermutationPartsFound++;
                }
            }
        }
        
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (howManyPermutationParts == howManyPermutationPartsFound) {
                return true;
            }

            int firstIndex = s2.charAt(i) - 'a';
            int newIndex = s2.charAt(i + s1.length()) - 'a';

            if (neededToFindFromArray[firstIndex] != 0) {
                neededToFindInArray[firstIndex]--;
                if (neededToFindInArray[firstIndex] + 1 == neededToFindFromArray[firstIndex]) {
                    howManyPermutationPartsFound--;
                }
            }

            if(neededToFindFromArray[newIndex] != 0) {
                neededToFindInArray[newIndex]++;
                if (neededToFindInArray[newIndex] == neededToFindFromArray[newIndex]) {
                    howManyPermutationPartsFound++;
                }
            }

        }

        return howManyPermutationParts == howManyPermutationPartsFound;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkInclusion("ab", "eidboaoo"));
    }
}