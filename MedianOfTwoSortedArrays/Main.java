
class Solution {
    public int checkValidity(int[] longer, int index, int value) {
        // -1 -> too small
        // 0 -> ideal
        // 1 -> too big
        if (longer[index] > value) {
            return -1;
        }
        else {
            return 1;
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int sumLength = nums1.length + nums2.length;
        int howManyBefore = (int)Math.ceil((float)sumLength / 2);

        int[] shorter = nums1.length > nums2.length ? nums2 : nums1;
        int[] longer = nums1.length > nums2.length ? nums1 : nums2;

        int shorterLow = 0;
        int shorterHigh = shorter.length - 1;

        int shorterLowValue = Integer.MIN_VALUE;
        int shorterHighValue = Integer.MAX_VALUE;

        int longerLowValue = Integer.MIN_VALUE;
        int longerHighValue = Integer.MAX_VALUE;

        int potentialMedianIndex = 0;


        if ((shorter.length == 0)) {
            if (sumLength % 2 == 0) {
                return ((double) (longer[howManyBefore] + longer[howManyBefore - 1]) / 2);
            } else {
                return longer[howManyBefore - 1];
            }
        }

        while (shorterLow <= shorterHigh) {
            int shorterMid = shorterLow + (shorterHigh - shorterLow) / 2;
            potentialMedianIndex = howManyBefore - shorterMid - 1;
            int result = checkValidity(longer, potentialMedianIndex, shorter[shorterMid]);
            if (result == 1) {
                shorterHighValue = shorter[shorterMid];
                longerLowValue = longer[potentialMedianIndex];
                shorterHigh = shorterMid - 1;
            } else {
                shorterLowValue = shorter[shorterMid];
                longerHighValue = longer[potentialMedianIndex];
                shorterLow = shorterMid + 1;
            }
        }

        if (shorterHighValue == Integer.MAX_VALUE && shorterLowValue != Integer.MIN_VALUE) {
            if (potentialMedianIndex - 1 >= 0) {
                longerLowValue = longer[potentialMedianIndex-1];
                longerHighValue = longer[potentialMedianIndex];
            }
        }

        if (shorterLowValue == Integer.MIN_VALUE && shorterHighValue != Integer.MAX_VALUE) {
            if (potentialMedianIndex + 1 < longer.length) {
                longerHighValue = longer[potentialMedianIndex+1];
                longerLowValue = longer[potentialMedianIndex];
            }
        }


        if (sumLength % 2 == 0) {
            return  ((double) (Math.min(shorterHighValue, longerHighValue) + Math.max(shorterLowValue, longerLowValue)) / 2);
        } else {
            return Math.max(shorterLowValue, longerLowValue);
        }


    }

}


class Main {
    public static void main(String[] args) {
        // Przypadek 1: Pełne przeplatanie (Równe długości, mediana w środku)
// Oczekiwana mediana: 20.5
        int[] nums1_case1 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39};
        int[] nums2_case1 = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40};

//// Przypadek 2: Twarde odcięcie (Równe długości, cała pierwsza przed drugą)
//// Oczekiwana mediana: 60.5
//        int[] nums1_case2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
//        int[] nums2_case2 = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120};
//
//// Przypadek 3: Nierówne długości, mediana ląduje w całości w dłuższej tablicy
//// Oczekiwana mediana: 23.0
//        int[] nums1_case3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
//        int[] nums2_case3 = {100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119};
//
//// Przypadek 4: Krawędziowy (bardzo krótka tablica z bardzo długą)
//// Oczekiwana mediana: 12.0
//        int[] nums1_case4 = {50, 60};
//        int[] nums2_case4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
//
//// Przypadek 5: Krótsza tablica w całości po lewej stronie (wszystkie jej elementy mniejsze od mediany)
//// Oczekiwana mediana: 12.0
//        int[] nums1_case5 = {-50, -50, -40, -35, -30, -30, -20, -15, -10, -10, -5, -5, -2, 0, 0, 1, 2, 2, 3, 4};
//        int[] nums2_case5 = {10, 14, 15, 15, 20, 20, 22, 25, 30, 30, 35, 35, 40, 40, 45, 50, 50, 55, 60, 60, 65, 70};
//
//// Przypadek 6: Przeplatające się tablice, mediana rozbita pomiędzy krótszą (50) i dłuższą (60)
//// Oczekiwana mediana: 55.0
//        int[] nums1_case6 = {10, 12, 14, 16, 18, 20, 22, 24, 26, 50, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88};
//        int[] nums2_case6 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 40, 60, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79};

        int[] nums1_case7 = {1,3};
        int[] nums2_case7 = {2};

// Wywołania:
        Solution solution = new Solution();
//        System.out.println("Test 1 (Oczekiwane 20.5): " + solution.findMedianSortedArrays(nums1_case1, nums2_case1));
//        System.out.println("Test 2 (Oczekiwane 60.5): " + solution.findMedianSortedArrays(nums1_case2, nums2_case2));
//        System.out.println("Test 3 (Oczekiwane 23.0): " + solution.findMedianSortedArrays(nums1_case3, nums2_case3));
//        System.out.println("Test 4 (Oczekiwane 12.0): " + solution.findMedianSortedArrays(nums1_case4, nums2_case4));
//        System.out.println("Test 5 (Oczekiwane 12.0): " + solution.findMedianSortedArrays(nums1_case5, nums2_case5));
//        System.out.println("Test 6 (Oczekiwane 55.0): " + solution.findMedianSortedArrays(nums1_case6, nums2_case6));
        System.out.println("Test 6 (Oczekiwane 55.0): " + solution.findMedianSortedArrays(nums1_case7, nums2_case7));

    }
}
