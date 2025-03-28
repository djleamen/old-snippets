// Description: This is a class that represents a time object.

/**
 * MyTime
 * 
 * Tracks time using a 24 hour clock. 
 * High School project. Practicing
 * JavaDoc and OOP principles.
 * 
 * @author DJ Leamen
 * @version 1.2
 * @since 2023-03-21
 */

public class MyTime {
    
    /**
     * The hour, minute, and second values of 
     * the clock.
     */
    private int hour;
    private int minute;
    private int second;
    
    /**
     * Initializes the hour, minute, and second values 
     * of the clock to zero.
     */
    public MyTime(){
        hour = 0;
        minute = 0;
        second = 0;
    }
    /**
     * Sets the hour, minute, and second values to
     * the values set by the user, as long as it
     * is in range (hour must be between 0-23, minute
     * must be between 0-59, second must be between 0-59.)
     * <p> Returns {@code "ERROR"} for invalid input, and
     * resets all values to zero.
     * 
     * @param hour The hour value of the clock.
     * @param minute The minute value of the clock.
     * @param second The second value of the clock.
     */
    public MyTime(int hour, int minute, int second) {
        if ((hour >= 0 || hour < 24) && (minute >= 0 || minute < 60) && (second >= 0 || second < 60)) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;}
        else {
          System.out.println("ERROR");
            this.hour = 0;
            this.minute = 0;
            this.second = 0;
        }
    }
    
    /**
     * Searches for the current value of "hour".
     * @return the current value held within the hour of the clock.
     */
    
    public int getHour() {
        return this.hour;
    }
    
    /**
     * Searches for the current value of "minute".
     * @return the current value held within the minute of the clock.
     */
    
    public int getMinute() {
        return this.minute;
    }
    
    /**
     * Searches for the current value of "second".
     * @return the current value held within the second of the clock.
     */
    
    public int getSecond() {
        return this.second;
    }
    
    /**
     * Updates the value of hour in the clock, as long as it 
     * is valid (i.e., within the 0-23 range.)
     * @param hour the value passed to update the clock's hour.
     */
    public void setHour(int hour) {
        if (hour < 0 || hour > 24) {
          System.out.println("ERROR");
        } else {
      this.hour = hour;
        }
    }
    
     /**
     * Updates the value of minute in the clock, as long as it 
     * is valid (i.e., within the 0-59 range.)
     * @param minute the value passed to update the clock's minute.
     */
    public void setMinute(int minute) {
        if (minute < 0 || minute > 60) {
          System.out.println("ERROR");
        } else {
            this.minute = minute;
        }
    }
    
     /**
     * Updates the value of second in the clock, as long as it 
     * is valid (i.e., within the 0-59 range.)
     * @param second  the value passed to update the clock's second.
     */
    public void setSecond(int second) {
        if (second < 0 || second > 60) {
          System.out.println("ERROR");
        } else {
      this.second = second;
        }
    }
    
    /**
     * Updates the clock to a new time set by the user, as long
     * as all values are valid and within range (hour must be between 0-23, minute
     * must be between 0-59, second must be between 0-59.)
     * <p> Returns {@code "ERROR"} for invalid input, and does not
     * update values of hour, minute, second.
     * @param hour The hour value of the clock.
     * @param minute The minute value of the clock.
     * @param second The second value of the clock.
     */
    public void setTime(int hour, int minute, int second) {
        if (hour < 0 || hour >= 24) {
          System.out.println("ERROR");
        }
        if (minute < 0 || minute >= 60) {
            System.out.println("ERROR");
        }
        if (second < 0 || second >= 60) {
            System.out.println("ERROR");
        } else {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
    }
    
    /**
     * Converts the time values to a string format following
     * HH:MM:SS, including following zeros in front of any single-digit
     * value of hour/minute/second 
     * @return The current time as a format of HH:MM:SS
     */
    public String toString() {
      return String.format("%02d:%02d:%02d", this.hour, this.minute, this.second);
    }
    
    /**
     * Updates the value of the clock after one second has passed.
     */
    public void nextSecond() {
        this.second = this.second + 1;
        if (this.second >= 60) {
            this.second = 0;
            this.minute = this.minute + 1;
            if (this.minute >= 60) {
                this.minute = 0;
                this.hour = this.hour + 1;
                if (this.hour >= 24) {
                    this.hour = 0;
                }
            }
        }
    }
    
    /**
     * Updates the value of the clock after one minute has passed.
     */
    public void nextMinute() {
        this.minute = this.minute + 1;
        if (this.minute >= 60) {
                this.minute = 0;
                this.hour = this.hour + 1;
                if (this.hour >= 24) {
                    this.hour = 0;
                }
        }
    }
    /**
     * Updates the value of the clock after one hour has passed.
     */
    public void nextHour() {
         this.hour = this.hour + 1;
         if (this.hour >= 24) {
             this.hour = 0; }
    }
    
    /**
     * Updates the value of the clock to the previous second.
     */
    public void previousSecond() {
         this.second = this.second - 1;
         if (this.second <= -1) {
            this.second = 59;
            this.minute = this.minute - 1;
            if (this.minute <= -1) {
                this.minute = 59;
                this.hour = this.hour - 1;
                if (this.hour <= -1) {
                    this.hour = 23;
                }
            }
         }
    }
    
    /**
     * Updates the value of the clock to the previous minute.
     */
    public void previousMinute() {
         this.minute = this.minute - 1;
            if (this.minute <= -1) {
                this.minute = 59;
                this.hour = this.hour - 1;
                if (this.hour <= -1) {
                    this.hour = 23;
                }
            }
    }
    
    /**
     * Updates the value of the clock to the previous hour.
     */
    public void previousHour() {
         this.hour = this.hour - 1;
         if (this.hour <= -1) {
             this.hour = 23;
         }
    }
}