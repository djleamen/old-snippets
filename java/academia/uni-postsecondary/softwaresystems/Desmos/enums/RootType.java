/*
 * Description: This enum class represents different types of roots 
 * (X-Intercept, Critical Point, Inflection Point) for a given function.
 * Each root type has a color, name, and associated function types for
 * calculating the roots. 
 */

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashSet;
import GraphGUI;
import Point;
import Function;

public enum RootType {

    X_INTERCEPT(Color.decode("#C2BCFF"), "X-INT", FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE),
    CRITICAL_POINT(Color.decode("#6330DB"), "CP", FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.decode("#D331E2"), "IP", FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE);

    private final Color color;
    private final String name;
    private final int ATTEMPTS = 100;
    private final FunctionType functionOne;
    private final FunctionType functionTwo;

    RootType(Color color, String name, FunctionType functionOne, FunctionType functionTwo) {
        this.color = color;
        this.name = name;
        this.functionOne = functionOne;
        this.functionTwo = functionTwo;
    }

    public Color getPointColor() {
        return color;
    }

    public String getPointName() {
        return name;
    }

    public HashSet<Point> getRoots(GraphGUI gui, Function function, double minDomain, double maxDomain) {
        DecimalFormat format = new DecimalFormat("0.000");
        format.setRoundingMode(RoundingMode.HALF_EVEN);
        HashSet<Point> addPoint = new HashSet<>();

        for (double i = minDomain - 0.01; i < maxDomain + 0.01; i += 0.01) {
            double x = newtonsMethod(function, i, ATTEMPTS);
            
            if (!Double.isNaN(x) && Double.parseDouble(format.format(function.getValueAt(x, FunctionType.ORIGINAL))) <= gui.getMaxRange() && Double.parseDouble(format.format(function.getValueAt(x, FunctionType.ORIGINAL))) >= gui.getMinRange() && Double.parseDouble(format.format(x)) <= maxDomain && Double.parseDouble(format.format(x)) >= minDomain) {
                Point point = new Point(gui, this, Double.parseDouble(format.format(x)), Double.parseDouble(format.format(function.getValueAt(x, FunctionType.ORIGINAL))));
                addPoint.add(point);
            }
        }
        return addPoint;
    }

    // Newton's Method
    // https://en.wikipedia.org/wiki/Newton%27s_method
    public double newtonsMethod(Function function, double guess, int attempts) {
        double epsilon = 1e-6; // Epsilon is the error margin
        for (int i = 0; i < attempts; i++) {
            double fValue = function.getValueAt(guess, functionOne);
            double fDerivative = function.getValueAt(guess, functionTwo);
            // If the derivative is 0, return NaN
            if (Math.abs(fDerivative) < epsilon) {
                return Double.NaN; 
            }
            double nextGuess = guess - fValue / fDerivative;
            // If the difference between the next guess and the current guess is less than the error margin, return the next guess
            if (Math.abs(nextGuess - guess) < epsilon) {
                return nextGuess; 
            }
            guess = nextGuess;
        }
        return Double.NaN; 
    }
}
