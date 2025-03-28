package csci2020u.lab09.components.functions;

import java.util.HashSet;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.Point;
import csci2020u.lab09.enums.FunctionType;

public class Tangent extends Trignometric {
    
    public Tangent(GraphGUI gui, String function) {
        super(gui, function, "tan");
    }

    @Override
    public String getFirstDerivative() {
        // a * k * sec^2(kx)
        return (float) (a * k) + "sec^2(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        // 2 * a * k^2 * sec^2(kx) * tan(kx)
        return (float) (2 * a * k * k) + "sec^2(" + k + "x)tan(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                // a * k * sec^2(kx)
                return a * k * Math.pow(1 / Math.cos(k * x), 2);
            case SECOND_DERIVATIVE:
                // 2 * a * k^2 * sec^2(kx) * tan(kx)
                return 2 * a * k * k * Math.pow(1 / Math.cos(k * x), 2) * Math.tan(k * x); 
            case THIRD_DERIVATIVE:
                // 2 * a * k^3 * (2 * sec^2(kx) * tan^2(kx) + sec^4(kx))
                double secSquared = Math.pow(1 / Math.cos(k * x), 2); // sec^2(kx)
                double tanSquared = Math.pow(Math.tan(k * x), 2); // tan^2(kx)
                double secFourth = Math.pow(1 / Math.cos(k * x), 4); // sec^4(kx)
                return 2 * a * Math.pow(k, 3) * (2 * secSquared * tanSquared + secFourth);
            default:
                // original - tan(kx)
                return a * Math.tan(k * x);
        }
    }

    @Override
    public HashSet<Point> getCriticalPoints() {
        return new HashSet<>();
    }

    @Override
    public HashSet<Point> getInflectionPoints() {
        return new HashSet<>();
    }
}
