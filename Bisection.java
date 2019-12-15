package findrootofafunction;

import javax.swing.JOptionPane;

public class Bisection {

    public static void bisection(String function, Double lowerBound, Double upperBound, Double decimalAccuracy) {

        // Create an array with 10 spaces
        Stack xValues  = new ArrayStack();
        Stack fxValues = new ArrayStack();

        double lowerX, upperX, newX, lowerFx, upperFx, newFx, difference;
        lowerX = lowerBound;
        upperX = upperBound;
        lowerFx = solveFx(function, lowerX);
        upperFx = solveFx(function, upperX);
        int iterations = 0;
        int maxIterations = 250;
        
        do {
            //Find new values of x and f(x)
           newX = (lowerX + upperX) / 2.0;
           newFx = solveFx(function, newX);
           //Calculate difference then add values to array
           difference = Math.abs(upperX - lowerX) / 2.0;
           xValues.push(newX);
           fxValues.push(newFx);

           //Decide which variable to replace based on the sign
           if (lowerFx * newFx > 0) {
               lowerX = newX;
               lowerFx = newFx;
           }
           else if (upperFx * newFx > 0 ) {
               upperX = newX;
               upperFx = newFx;
           }
           // Increment iterations
           iterations++;
        }
        while(difference > decimalAccuracy && iterations <= maxIterations);
        if (iterations >= maxIterations) {
            JOptionPane.showMessageDialog(null,
                    "No root was found after " + maxIterations + " iterations using the Bisection Method",
                    "No root found!",
                    JOptionPane.ERROR_MESSAGE
                    );
        }
        else {
            NewGraphAndTable bisectionGraph = new NewGraphAndTable();
            bisectionGraph.createDataFromStack("Root of " + function + " using Bisection", xValues, fxValues);
        }
    }

    private static double solveFx(String function, double x) {
        double fx = 0.0;
        if (function == "x-x^2") {
            fx = x - Math.pow(x, 2);
        }
        else if (function == "ln(x+1)+1") {
            fx = Math.log(x + 1.0) + 1.0;
        }
        else if (function == "e^x-3x") {
            double e = 2.71828; //Euler's Constant
            fx = Math.pow(e, x) - (3.0 * x);
        }
        return fx;
    }
}
