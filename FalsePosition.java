package findrootofafunction;

import javax.swing.JOptionPane;

public class FalsePosition {

    public static void falsePosition (String function, Double lowerBound, Double upperBound, Double decimalAccuracy) {
        //Create a linked list and sets the header items
        LinkedList output = new LinkedList();
        output.head.setX(lowerBound);
        output.head.setFx(solveForfx(function, lowerBound));
        double newX, fx1, fx2, newFx;
        double x1 = lowerBound;
        double x2 = upperBound;
        int iterations = 0;
        int maxIterations = 250;

        do {
            //Find both fx Values
            fx1 = solveForfx(function, x1);
            fx2 = solveForfx(function, x2);
            //Find the newX value and it's function value
            newX = x2 - fx2 * (x1 - x2) / (fx1 - fx2);
            newFx = solveForfx(function, newX);
            //Add new values to list
            output.addLast(newX, newFx);
            //Decide based on sign which value of x to replace
            if (newFx * fx1 < 0) {
                x2 = newX;
            }
            else {
                x1 = newX;
            }
            //Increment iterations
            iterations++;
        }
        while (Math.abs(newFx) >= decimalAccuracy && iterations <= maxIterations);
        if (iterations >= maxIterations) {
            JOptionPane.showMessageDialog(null,
                    "No root was found after " + maxIterations + " iterations using the False Position Method",
                    "No root found!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else {
            NewGraphAndTable falsePositionGraph = new NewGraphAndTable();
            falsePositionGraph.createDataFromList("Root of " + function + " using False Position", output);
        }
    }

    private static double solveForfx(String function, double x) {
        double fx = 0.0;
        if(function == "x-x^2") {
            fx = x - Math.pow(x, 2);
        }
        else if (function == "ln(x+1)+1") {
            fx = Math.log(x + 1.0) + 1.0;
        }
        else if (function == "e^x-3x") {
            double e = 2.71828;
            fx = Math.pow(e, x) - (3.0 * x);
        }
        return fx;
    }
}
