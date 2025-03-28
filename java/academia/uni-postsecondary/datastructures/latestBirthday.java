package datastructures;
// Description: This program finds the latest birthday in an array of dates.

public class latestBirthday {

    public static int findLatestBirthday(int[] dates, int low, int high) {
        if (high == 0) {
          return -1;
        } else if (low == high) {
          return dates[low];
        } else {
          int midpoint = (low + high) / 2;
          return Math.max(findLatestBirthday(dates, low, midpoint), 
              findLatestBirthday(dates, midpoint+1, high));
        }
      }

      public static void main(String[] args) {
        int[] dates = {123, 126, 45, 243};
        System.out.println(findLatestBirthday(dates, 0, dates.length-1));
      }

}

