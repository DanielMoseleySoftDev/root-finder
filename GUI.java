package findrootofafunction;

import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame = new JFrame("Roots of a Function");
    private JPanel containerPanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private XYChart lineChart = new XYChart(400, 400);
    private JComboBox methodCombo = new JComboBox();
    private JComboBox functionCombo = new JComboBox();
    private JTextField decimalTolerance = new JTextField();
    private JTextField startingPoint = new JTextField();
    private JTextField lowerBound = new JTextField();
    private JTextField upperBound = new JTextField();
    private JLabel methodLabel = new JLabel("Select Root Finding Method - ");
    private JLabel functionLabel = new JLabel("Select Function - ");
    private JLabel decimalLabel = new JLabel("Select decimal points - ");
    private JLabel startingPointLabel = new JLabel("Enter Starting X Value - ");
    private JLabel lowerBoundLabel = new JLabel("Enter Lower Bound X Value - ");
    private JLabel upperBoundLabel = new JLabel("Enter Upper Bound X Value - ");
    private JButton graphButton = new JButton("Plot Results");
    private CardLayout cardLayout = new CardLayout();
    private GridBagLayout gridBagLayout = new GridBagLayout();
    private GridBagConstraints gc = new GridBagConstraints();

    //Constructs the GUI
    public void createGUI() {

        //combo box set up
        methodCombo.setPreferredSize(new Dimension(200, 25));
        functionCombo.setPreferredSize(new Dimension(200, 25));
        methodCombo.setEditable(false);
        functionCombo.setEditable(false);

        //combo box items
        methodCombo.addItem("Newton-Raphson");
        methodCombo.addItem("Secant");
        methodCombo.addItem("Bisection");
        methodCombo.addItem("False Position");
        functionCombo.addItem("x-x^2");
        functionCombo.addItem("ln(x+1)+1");
        functionCombo.addItem("e^x-3x");

        //text field set up
        startingPoint.setPreferredSize(new Dimension(200, 25));
        decimalTolerance.setPreferredSize(new Dimension(200, 25));
        lowerBound.setPreferredSize(new Dimension(200, 25));
        upperBound.setPreferredSize(new Dimension(200, 25));
        //hides the upperbound and lowerbound fields + labels until a method that requires them is selected
        lowerBound.setVisible(false);
        lowerBoundLabel.setVisible(false);
        upperBound.setVisible(false);
        upperBoundLabel.setVisible(false);

        //sets the layout of the panels
        containerPanel.setLayout(cardLayout);
        mainPanel.setLayout(gridBagLayout);


        //add elements to grid-bag positions of main panel
        //labels added below
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.25;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        mainPanel.add(methodLabel, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        mainPanel.add(functionLabel, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        mainPanel.add(decimalLabel, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        mainPanel.add(startingPointLabel, gc);
        mainPanel.add(lowerBoundLabel, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        mainPanel.add(upperBoundLabel, gc);
        //combo boxes added below
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;
        mainPanel.add(methodCombo, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        mainPanel.add(functionCombo, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        //text fields added below
        mainPanel.add(decimalTolerance, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        mainPanel.add(startingPoint, gc);
        mainPanel.add(lowerBound, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        mainPanel.add(upperBound, gc);
        //button added below
        gc.weighty = 0;
        gc.gridx = 1;
        gc.gridy = 5;
        mainPanel.add(graphButton, gc);

        //adds the panels to the container
        containerPanel.add(mainPanel, "mainpanel");

        //sets initial start panel to mainpanel
        cardLayout.show(containerPanel, "mainpanel");

        //action listeners
        //adds functionality to the plot results button
        graphButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //selects the desired method
               if (methodCombo.getSelectedItem() == "Newton-Raphson") {
                   //ensures valid inputs
                   if (checkForNumber(startingPoint) && checkForDecimal(decimalTolerance)) {
                           NewtonRaphson.newtonRaphson(functionCombo.getSelectedItem().toString(),
                                   Double.parseDouble(startingPoint.getText()),
                                   Double.parseDouble(decimalTolerance.getText()));
                       }
                   else {
                       JOptionPane.showMessageDialog(frame,
                               "Please enter an appropriate value for all fields. " +
                                       "\nTip: Decimal points should be written as a value between 0.1 and 0.000000001.",
                               " Please enter values",
                               JOptionPane.ERROR_MESSAGE );
                       }
               }
               else if (methodCombo.getSelectedItem() == "Bisection") {
                   if (checkForNumber(upperBound) && checkForNumber(lowerBound) && checkForDecimal(decimalTolerance)) {
                       Bisection.bisection(functionCombo.getSelectedItem().toString(),
                               Double.parseDouble(lowerBound.getText()),
                               Double.parseDouble(upperBound.getText()),
                               Double.parseDouble(decimalTolerance.getText()));
                   }
                   else {
                       JOptionPane.showMessageDialog(frame,
                               "Please enter an appropriate value for all fields. " +
                                       "\nTip: Decimal points should be written as a value between 0.1 and 0.000000001.",
                               " Please enter values",
                               JOptionPane.ERROR_MESSAGE );
                   }
               }
               else if(methodCombo.getSelectedItem() == "Secant") {
                   if (checkForNumber(upperBound) && checkForNumber(lowerBound) && checkForDecimal(decimalTolerance)) {
                       Secant.secant(functionCombo.getSelectedItem().toString(),
                               Double.parseDouble(lowerBound.getText()),
                               Double.parseDouble(upperBound.getText()),
                               Double.parseDouble(decimalTolerance.getText()));
                   }
                   else {
                       JOptionPane.showMessageDialog(frame,
                               "Please enter an appropriate value for all fields. " +
                                       "\nTip: Decimal points should be written as a value between 0.1 and 0.000000001.",
                               " Please enter values",
                               JOptionPane.ERROR_MESSAGE );
                   }
               }
               else if(methodCombo.getSelectedItem() == "False Position") {
                   if (checkForNumber(upperBound) && checkForNumber(lowerBound) && checkForDecimal(decimalTolerance)) {
                       FalsePosition.falsePosition(functionCombo.getSelectedItem().toString(),
                               Double.parseDouble(lowerBound.getText()),
                               Double.parseDouble(upperBound.getText()),
                               Double.parseDouble(decimalTolerance.getText()));
                   }
                   else {
                       JOptionPane.showMessageDialog(frame,
                               "Please enter an appropriate value for all fields. " +
                                       "\nTip: Decimal points should be written as a value between 0.1 and 0.000000001.",
                               " Please enter values",
                               JOptionPane.ERROR_MESSAGE );
                   }
               }

               frame.setSize(new Dimension(500, 250));
               cardLayout.show(containerPanel, "graphpanel");
           }
        });

        //Action listener for combo box that selects method
        methodCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (methodCombo.getSelectedItem() == "Newton-Raphson") {
                    //Shows only necessary entry fields for the  method
                    upperBound.setVisible(false);
                    upperBoundLabel.setVisible(false);
                    lowerBound.setVisible(false);
                    lowerBoundLabel.setVisible(false);
                    startingPoint.setVisible(true);
                    startingPointLabel.setVisible(true);
                    frame.pack();
                }
                else if(methodCombo.getSelectedItem() == "Bisection"
                        || methodCombo.getSelectedItem() == "Secant"
                        || methodCombo.getSelectedItem() == "False Position") {
                    //Shows only necessary entry fields for the method
                    upperBound.setVisible(true);
                    upperBoundLabel.setVisible(true);
                    lowerBound.setVisible(true);
                    lowerBoundLabel.setVisible(true);
                    startingPoint.setVisible(false);
                    startingPointLabel.setVisible(false);
                    frame.pack();
                }
            }
        });

        //Frame set up
        frame.setPreferredSize(new Dimension(500, 250));
        frame.setResizable(true);
        frame.add(containerPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    //Method to ensure that only numbers can be accepted into the text fields
    private boolean checkForNumber(JTextField textField) {
        String value = textField.getText();
        try {
            Double.parseDouble(value);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkForDecimal(JTextField textField) {
       if(checkForNumber(textField)) {
           String value = textField.getText();
           double temp = Double.parseDouble(value);
           if (temp < 1 && temp >= 0.000000001) {
               return true;
           }
           else {
               return false;
           }
        }
       else {
           return false;
       }
    }
}
