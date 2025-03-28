package datastructures;
// Description: A class that creates a rectangle object with a length and width and displays the rectangle with the given dimensions.

public class Rectangle {
    
    private int length;
    private int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void displayRect() {
        for (int w = 1; w <= getWidth(); w++) {
            System.out.print(" -");
        }
        System.out.println(); 

        for (int l = 1; l <= getLength(); l++) {
            System.out.print("|");
            for (int w = 1; w <= getWidth(); w++) {
                System.out.print("  ");
            }
            System.out.print("|");
            System.out.println();
        }
        for (int w = 1; w <= getWidth(); w++) {
            System.out.print(" -");
        }
        System.out.println(); // Move to the next line after the bottom border
    }
    public static void main(String[] args) {
        Rectangle square = new Rectangle(5, 5);
        square.displayRect();
    }
}