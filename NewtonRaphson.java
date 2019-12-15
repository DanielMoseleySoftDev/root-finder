/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findrootofafunction;

import javax.swing.JOptionPane;

public class NewtonRaphson extends GUI {

    public static void newtonRaphson(String function, Double startingPoint, Double decimalAccuracy) {
        //Create a linked list and sets the header items
        LinkedList output = new LinkedList();
        output.head.setX(startingPoint);
        output.head.setFx(solveForfx(function, startingPoint));

        double xNew, difference;
        double xOld = startingPoint;
        double fxOld = 0;
        double fDashxOld = 0;
        int iterations = 0;
        int maxIterations = 250;

        do {

            //Value of f(x) & f'(x) chosen based on function
            if (function == "x-x^2") {
                fxOld = solveForfx("x-x^2", xOld);
                fDashxOld = 1.0 - (2.0 * xOld);
            }
            else if (function == "ln(x+1)+1") {
                fxOld = solveForfx("ln(x+1)+1", xOld);
                fDashxOld =  1.0 / xOld + 1.0;
            }
            else if (function == "e^x-3x") {
                double e = 2.71828; //Euler's Constant
                fxOld = solveForfx("e^x-3x", xOld);
                fDashxOld = Math.pow(e, xOld) - 3.0;;
            }
            //Find next iteration of x and add it to the list
            xNew = xOld - (fxOld / fDashxOld);
            output.addLast(xNew, solveForfx(function, xNew));
            //Calculate the difference compared to the desired decimal input
            difference = Math.abs(xNew - xOld);
            //Set the new value of x to be used for the next iteration, increment counter
            xOld = xNew;
            iterations++;
        }
        while (difference > decimalAccuracy && iterations <= maxIterations);
        if (iterations >= maxIterations) {
            JOptionPane.showMessageDialog(null,
                    "No root was found after " + maxIterations + " iterations using the Newton Raphson Method",
                    "No root found!",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else {
            NewGraphAndTable newtonGraph = new NewGraphAndTable();
            newtonGraph.createDataFromList("Root of " + function + " using Newton Raphson", output);
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
            double e = 2.71828; //Euler's constant
            fx = Math.pow(e, x) - (3.0 * x);
        }
        return fx;
    }
}
