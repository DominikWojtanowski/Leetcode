class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int weightsHigh = 0;
        int weightsLow = Integer.MIN_VALUE;
        int weightMid;

        for (int weight : weights) {
            weightsLow = Math.max(weightsLow, weight);
            weightsHigh += weight;
        }

        while (weightsLow <= weightsHigh) {
            weightMid = weightsLow + (weightsHigh - weightsLow) / 2;
            int load = 0;
            int time = 0;
            for (int j : weights) {
                load += j;
                if (load > weightMid) {
                    load = j;
                    time += 1;
                    if (time >= days) {
                        break;
                    }
                }
            }
            if (time >= days) {
                weightsLow = weightMid + 1;
            } else {
                weightsHigh = weightMid - 1;
            }

        }

        return weightsLow;
    }
}