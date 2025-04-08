/*
 * Description: This class represents a logarithmic function 
 * and provides methods to calculate its first and second derivatives, 
 * critical points, inflection points, and value at a given x.
 */

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import GraphGUI;
import Point;
import FunctionType;

public class Logarithmic extends Function {

    public double a;
    public double base;
    public double k;

    public Logarithmic(GraphGUI gui, String function) {
        super(gui);
        Pattern sortLog = Pattern.compile("^([+\\-])?(\\d+(\\.\\d+)?)?(log([+\\-])?(\\d+(\\.\\d+)?)?|ln)\\(([+\\-])?(\\d+(.\\d+)?)?x\\)$");
        Matcher matcher = sortLog.matcher(function);

        if (matcher.find()) {
            a = (matcher.group(2) == null) ? (matcher.group(1) != null) ? (matcher.group(1).equals("-")) ? -1 : 1 : 1 : (matcher.group(1) != null) ? (matcher.group(1).equals("-")) ? -Double.parseDouble(matcher.group(2)) : Double.parseDouble(matcher.group(2)) : Double.parseDouble(matcher.group(2));
            k = (matcher.group(9) == null) ? (matcher.group(8) != null) ? (matcher.group(8).equals("-")) ? -1 : 1 : 1 : (matcher.group(8) != null) ? (matcher.group(8).equals("-")) ? -Double.parseDouble(matcher.group(9)) : Double.parseDouble(matcher.group(9)) : Double.parseDouble(matcher.group(9));
            base = (matcher.group(4).contains("log")) ? (matcher.group(5) != null) ? (matcher.group(5).equals("-")) ? (matcher.group(6) == null) ? -10 : -Double.parseDouble(matcher.group(6)) : (matcher.group(6) == null) ? 10 : Double.parseDouble(matcher.group(6)) : (matcher.group(6) == null) ? 10 : Double.parseDouble(matcher.group(6)) : (matcher.group(5) != null ? (matcher.group(5).equals("-")) ? -Math.E : Math.E : Math.E);
        }
    }

    @Override
    public String getFirstDerivative() {
        return (base == Math.E) ? a + "/x" : a + "/(xln(" + base + "))";
    }

    @Override
    public String getSecondDerivative() {
        return (base == Math.E) ? -a + "/x^2" : -a + "/(x^2ln(" + base + "))";
    }

    @Override
    public HashSet<Point> getCriticalPoints() {
        return new HashSet<>();
    }

    @Override
    public HashSet<Point> getInflectionPoints() {
        return new HashSet<>();
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        return (functionType == FunctionType.FIRST_DERIVATIVE) ? a / x * Math.log(base) : a * Math.log10(k * x) / Math.log10(base);
    }
}
