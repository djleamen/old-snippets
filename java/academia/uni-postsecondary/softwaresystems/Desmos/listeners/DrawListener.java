package csci2020u.lab09.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import csci2020u.lab09.GraphGUI;
import csci2020u.lab09.components.GraphPanel;
import csci2020u.lab09.components.functions.*;

public class DrawListener implements ActionListener {

    private final GraphGUI gui;
    private final GraphPanel panel;

    public DrawListener(GraphGUI gui, GraphPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            Pattern checkPolynomial = Pattern.compile("^(y=|f\\(x\\)=)?((-?\\d+\\.?\\d*)?(x(\\^\\d+)?)?)((\\+(?!\\+)(\\d+\\.?\\d*)?|-(?!-)(\\d+\\.?\\d*)?)((x(\\^\\d+)?)?))*$");
            Pattern checkTrigLog = Pattern.compile("^([+\\-])?(\\d+(.\\d+)?)?(sin|cos|tan|log([+\\-])?(\\d+(.\\d+)?)?|ln)\\(([+\\-])?(\\d+(.\\d+)?)?x\\)$");
            Matcher matchPolynomial = checkPolynomial.matcher(gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", ""));
            Matcher matchTrigLog = checkTrigLog.matcher(gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", ""));

            if (matchPolynomial.find()) {
                if (matchPolynomial.group().isEmpty() || gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", "").substring(gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", "").length() - 1).equals("+") || gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", "").substring(gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", "").length() - 1).equals("-")) {
                    throw new Exception();
                } else {
                    panel.setFunction(new Polynomial(gui, gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", "")));
                }
            } else if (matchTrigLog.find()) {
                if (gui.getEquationField().contains("sin")) {
                    panel.setFunction(new Sine(gui, gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", "")));
                } else if (gui.getEquationField().contains("cos")) {
                    panel.setFunction(new Cosine(gui, gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", "")));
                } else if (gui.getEquationField().contains("tan")) {
                    panel.setFunction(new Tangent(gui, gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", "")));
                }  else if (gui.getEquationField().contains("log") || gui.getEquationField().contains("ln")) {
                    panel.setFunction(new Logarithmic(gui, gui.getEquationField().replaceAll("\\s+", "").replaceAll("f\\(x\\)=", "").replaceAll("y=", "")));
                }
            } else {
                throw new Exception();
            }
            gui.setFirstDerivativeLabel("f'(x)=" + panel.getFunction().getFirstDerivative());
            gui.setSecondDerivativeLabel("f''(x)=" + panel.getFunction().getSecondDerivative());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, gui.getEquationField() + " is not a supported function!", "Not Supported Function", JOptionPane.ERROR_MESSAGE);
        }
        panel.repaint();
    }
}
