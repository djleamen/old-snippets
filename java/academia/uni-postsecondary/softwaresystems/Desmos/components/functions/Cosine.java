package csci2020u.lab09.components.functions;

import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.enums.FunctionType;

public class Cosine extends Trignometric {
    
    public Cosine(GraphGUI gui, String function) {
        super(gui, function, "cos");
    }

    @Override
    public String getFirstDerivative() {
        // -a * k * sin(kx)
        return (float) (-a * k) + "sin(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        // -a * k^2 * cos(kx)
        return (float) (-a * Math.pow(k, 2)) + "cos(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                // -a * k * sin(kx)
                return -a * k * Math.sin(k * x);
            case SECOND_DERIVATIVE:
                // -a * k^2 * cos(kx)
                return -a * Math.pow(k, 2) * Math.cos(k * x);
            case THIRD_DERIVATIVE:
                // -a * k^3 * sin(kx)
                return a * Math.pow(k, 3) * Math.sin(k * x);
            default: 
                // original - a * cos(kx)
                return a * Math.cos(k * x); 
        }
    }
}
