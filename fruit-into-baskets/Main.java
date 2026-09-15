class Solution {

    public int totalFruit(int[] fruits) {
        // 0 index is the last index in which this type occurred, 1 index is count of fruits of this type
        int[] firstUniqueType = new int[2];
        int[] secondUniqueType = new int[2];

        int maxCount = Integer.MIN_VALUE;

        // we will select one unique letter at index 0
        firstUniqueType[1] = 1;

        // firstly we will try to find two unique types
        for (int i = 1; i < fruits.length; i++) {
            if (fruits[i] != fruits[firstUniqueType[0]]) {
                secondUniqueType[0] = i;
                secondUniqueType[1]++;
                break;
            }
            firstUniqueType[0] = i;
            firstUniqueType[1]++;
        }

        maxCount = Math.max(maxCount, firstUniqueType[1] + secondUniqueType[1]);

        //now we do main loop
        int startingIndex = Math.max(firstUniqueType[0],secondUniqueType[0]);
        for (int i = startingIndex + 1; i < fruits.length; i++) {
            int type = fruits[i];
            // if it's the same type as first unique type
            if (type == fruits[firstUniqueType[0]]) {
                firstUniqueType[0] = i;
                firstUniqueType[1]++;

            }  // if it's the same type as second unique type
            else if (type == fruits[secondUniqueType[0]]) {
                secondUniqueType[0] = i;
                secondUniqueType[1]++;
            } // if it's new type we will need to change some letter
            else {
                // second unique type is needed to be deleted
                // and first unique type is needed to be decreased
                if (firstUniqueType[0] > secondUniqueType[0]) {
                    firstUniqueType[1] = firstUniqueType[0] - secondUniqueType[0];
                    secondUniqueType[0] = i;
                    secondUniqueType[1] = 1;
                }
                // first unique type is needed to be deleted
                // and second unique type is needed to be decreased
                else {
                    secondUniqueType[1] = secondUniqueType[0] - firstUniqueType[0];
                    firstUniqueType[0] = i;
                    firstUniqueType[1] = 1;
                }

            }
            maxCount = Math.max(maxCount, firstUniqueType[1] + secondUniqueType[1]);

        }

        return maxCount;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.totalFruit(new int[]{0,1,2,2}));
    }
}