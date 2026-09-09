import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int characterReplacement(String s, int k) {
        ArrayDeque<Integer> kStack = new ArrayDeque<>();
        HashMap<Character, ArrayList<Integer>> sHashMap = new HashMap<>(26,1);

        int currentLength;
        int best = Integer.MIN_VALUE;

        int connectionBeginningIndex = 0;
        int sourceSize = s.length();

        for (int i = 0; i < sourceSize; i++) {
            char c = s.charAt(i);
            sHashMap.computeIfAbsent(c, (_) -> new ArrayList<>()).add(i);
        }

        for(ArrayList<Integer> value : sHashMap.values()) {
            int left = k;
            int vSize = value.size();
            currentLength = 0;
            kStack.clear();

            if (!value.isEmpty()) {
                connectionBeginningIndex = value.getFirst();
            }

            for (int i = 0; i < vSize; i++) {
                currentLength++;
                if (i + 1 == vSize) {
                    // this is the last element of particular letter and we need to handle expansion logic
                    // we can't expand in all capacity to the right
                    if (value.get(i) + left >= sourceSize) {
                        // we expand maximally to the right;
                        int expandToRight = sourceSize - (value.get(i) + 1);
                        left -= expandToRight;
                        currentLength += expandToRight;

                        // if we can't add all remaining transformations to left we add all we can
                        currentLength += Math.min(connectionBeginningIndex, left);
                    } else {
                        // we expand maximally to the right
                        currentLength += left;
                    }

                    best = Math.max(best,currentLength);
                } else {
                    // we add current element because it doesn't cost us anything
                    best = Math.max(best, currentLength);
                    // value.get(i + 1) - value.get(i) -> distance between elements
                    // value.get(i + 1) - value.get(i) - 1 -> distance between elements - already made element
                    int bridgeConnectionCost = value.get(i + 1) - value.get(i) - 1;

                    // we don't have enough transformations to create bridge connectoin
                    if (left - bridgeConnectionCost < 0) {
                        // there is possibility that using remaining transformations will give us maximum result
                        best = Math.max(best, currentLength + left);
                        // we remove enough bridge connections or all to have biggest possibility to create new connections
                        while (!kStack.isEmpty() && left - bridgeConnectionCost < 0) {
                            int connectionAddedLength = kStack.pollFirst();
                            // we decrease it by one because we don't include element which connection was coming from
                            left += connectionAddedLength - 1;
                            // we need to decrease length with element which connection was coming from included
                            currentLength -= connectionAddedLength;
                            // we move connectionBeginningIndex to right to set new beginning
                            connectionBeginningIndex += connectionAddedLength - 1;
                        }

                        // we need to start from next element because even after removing all connections we don't have enough transformations left
                        if (left - bridgeConnectionCost < 0) {
                            currentLength = 0;
                            left = k;
                        } else {
                            left -= bridgeConnectionCost;
                            currentLength += bridgeConnectionCost;
                            // we add bridge connection cost and the element which connection was coming from
                            kStack.add(bridgeConnectionCost + 1);
                        }
                    } else {
                        left -= bridgeConnectionCost;
                        currentLength += bridgeConnectionCost;
                        // we add bridge connection cost and the element which connection was coming from
                        kStack.add(bridgeConnectionCost + 1);
                    }
                }

            }

        }
        return best;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.characterReplacement("EOEMQLLQTRQDDCOERARHGAAARRBKCCMFTDAQOLOKARBIJBISTGNKBQGKKTALSQNFSABASNOPBMMGDIOETPTDICRBOMBAAHINTFLH",7));
    }
}