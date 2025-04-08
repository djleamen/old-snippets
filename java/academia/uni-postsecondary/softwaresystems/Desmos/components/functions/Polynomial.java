/*
 * Description: Polynomial class for parsing and evaluating 
 * polynomial functions. It can handle polynomials of any degree and
 * provides methods to calculate the first and second derivatives.
 * The class also includes a method to evaluate the polynomial at a
 * given x-coordinate, depending on the type of function (original,
 * first derivative, second derivative, or third derivative).
 */

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import GraphGUI;
import FunctionType;

public final class Polynomial extends Function {

    private double[] coefficients = new double[0];
    private int[] degrees = new int[0];

    public Polynomial(GraphGUI gui, String polynomial) {
        super(gui);
        Pattern sortPolynomial = Pattern.compile("(([+\\-])?(\\d+\\.?\\d*x|\\d+\\.?\\d*|x))(\\^\\d+)?");
        Matcher matcher = sortPolynomial.matcher(polynomial);
        
        for (int i = 0; matcher.find(); i++) {
            coefficients = Arrays.copyOf(coefficients, coefficients.length + 1);
            degrees = Arrays.copyOf(degrees, degrees.length + 1);

            if (matcher.group(1).contains("x")) {
                if (matcher.group(4) == null) {
                    degrees[i] = 1;
                } else {
                    degrees[i] = Integer.parseInt(matcher.group(4).replace("^", ""));
                }
                switch (matcher.group(1)) {
                    case "x":
                    case "+x":
                        coefficients[i] = 1;
                        break;
                    case "-x":
                        coefficients[i] = -1;
                        break;
                    default:
                        coefficients[i] = Double.parseDouble(matcher.group(1).replace("x", ""));
                        break;
                }
            } else {
                coefficients[i] = Double.parseDouble(matcher.group(1));
                degrees[i] = 0;
            }
        }
    }

    @Override
    public String getFirstDerivative() {
        StringBuilder s = new StringBuilder();
        DecimalFormat f = new DecimalFormat();

        double[] coefficient = new double[coefficients.length];
        int[] degree = new int[degrees.length];

        for (int i = 0; i < coefficient.length; i++) {
            coefficient[i] = degrees[i] * coefficients[i];
            degree[i] = (degrees[i] > 0) ? degrees[i] - 1 : degrees[i];
        }

        for (int i = 0; i < degree.length; i++) {
            if (coefficient[i] != 0) {
                if (degree[i] > 1) {
                    s.append(f.format(coefficient[i])).append("x^").append(degree[i]);
                } else if (degree[i] == 1) {
                    s.append(f.format(coefficient[i])).append("x");
                } else if (degree[i] == 0 && coefficient[i] != 0) {
                    s.append(f.format(coefficient[i]));
                }
            }
            if (i + 1 < coefficient.length) {
                if (coefficient[i + 1] > 0 && (s.length() > 0)) {
                    s.append("+");
                }
            }
        }
        return s.toString();
    }

    @Override
    public String getSecondDerivative() {
        return new Polynomial(gui, getFirstDerivative()).getFirstDerivative();
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return new Polynomial(gui, getFirstDerivative()).getValueAt(x, FunctionType.ORIGINAL);
            case SECOND_DERIVATIVE:
                return new Polynomial(gui, getSecondDerivative()).getValueAt(x, FunctionType.ORIGINAL);
            case THIRD_DERIVATIVE:
                return new Polynomial(gui, new Polynomial(gui, getSecondDerivative()).getFirstDerivative()).getValueAt(x, FunctionType.ORIGINAL);
            default:
                double y = 0;
                for (int i = 0; i < coefficients.length; i++) {
                    y += coefficients[i] * Math.pow(x, degrees[i]);
                }
                return y;
        }
    }
}
