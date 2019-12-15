package findrootofafunction;

import javax.swing.JOptionPane;

public class Secant {

    public static void secant(String function, Double lowerBound, Double upperBound, Double decimalAccuracy) {
        //Create Array Stacks to store values
        Stack xValues = new ArrayStack();
        Stack yValues = new ArrayStack();
        double lowerX, upperX, newX, lowerFx, upperFx, difference,newFx;
        lowerX = lowerBound;
        upperX = upperBound;
        int iterations = 0;
        int maxIterations = 250;

        do {
            //Differentiate the upper and lower x values
            lowerFx = solveFx(function, lowerX);
            upperFx = solveFx(function, upperX);
            //Find the next values of x and Fx
            newX = lowerX - (lowerFx * (lowerX - upperX)) /(lowerFx - upperFx);
            newFx = solveFx(function, newX);
            //Add values to Array Stacks
            xValues.push(newX);
            yValues.push(newFx);
            //Calculate difference to be used for stop check
            difference = Math.abs(newX - lowerX);
            //Set new upper and lower values of x
            upperX = lowerX;
            lowerX = newX;
            // Increment iterations
            iterations++;
        }
        while(difference > decimalAccuracy && iterations <= maxIterations);
        if (iterations >= maxIterations) {
            JOptionPane.showMessageDialog(null,
                    "No root was found after " + maxIterations + " iterations using the Secant Method",
                    "No root found!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else {
            NewGraphAndTable secantGraph = new NewGraphAndTable();
            secantGraph.createDataFromStack("Root of " + function + " using Secant", xValues, yValues);
        }
    }

    private static double solveFx(String function, double x) {
        double fx = 0.0;
        if (function == "x-x^2") {
            fx = x - Math.pow(x, 2);
        }
        else if (function == "ln(x+1)+1") {
            fx = Math.abs(Math.log(x + 1.0) + 1.0);
        }
        else if (function == "e^x-3x") {
            double e = 2.71828; //Euler's Constant
            fx = Math.pow(e, x) - (3.0 * x);
        }
        return fx;
    }
}

