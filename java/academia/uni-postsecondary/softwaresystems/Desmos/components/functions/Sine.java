package csci2020u.lab09.components.functions;

import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.enums.FunctionType;

public class Sine extends Trignometric {

    public Sine(GraphGUI gui, String function) {
        super(gui, function, "sin");
    }

    @Override
    public String getFirstDerivative() {
        return (float) (a * k) + "cos(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return (float) (-a * Math.pow(k, 2)) + "sin(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return a * k * Math.cos(k * x);
            case SECOND_DERIVATIVE:
                return -a * Math.pow(k, 2) * Math.sin(k * x);
            case THIRD_DERIVATIVE:
                return -a * Math.pow(k, 3) * Math.cos(k * x);
            default:
                return a * Math.sin(k * x);
        }
    }
}
