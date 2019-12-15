package findrootofafunction;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;


public class NewGraphAndTable {
    public XYChart currentChart = new XYChart(400, 400);
    public JPanel chartPanel = new XChartPanel<>(currentChart);
    public JPanel tablePanel = new JPanel();
    public JFrame dataFrame = new JFrame();

    public void createDataFromStack(String title, Stack xData, Stack yData) {

        double[] tempXValues = new double[xData.size()];
        double[] tempYValues = new double[yData.size()];
        int i = 0;

        // Empties the stacks and adds them to simple arrays which can be used for graph and table creation
        while (!xData.isEmpty() && !yData.isEmpty()) {
            double currentX = new Double(xData.pop().toString());
            double currentY = new Double(yData.pop().toString());
            tempXValues[i] = currentX;
            tempYValues[i] = currentY;
            i++;
        }
        combineGraphAndTable(title, tempXValues, tempYValues);
    }

    public void createDataFromList(String title, LinkedList list){

        double[] tempXValues = new double[list.getSize()];
        double[] tempYValues = new double[list.getSize()];
        int i = list.getSize() - 1; //adds to the array backwards since the list head will be the last iteration

        // Adds values from the lists into simple arrays
        while (list.head != null) {
            tempXValues[i] = list.head.getX();
            tempYValues[i] = list.head.getFx();
            list.head = list.head.getNext();
            i--;
        }
        combineGraphAndTable(title, tempXValues, tempYValues);
    }

    public JTable createTable(double[] xValues, double[] fxValues) {
        // Names of table columns
        String[] columnNames = {"Iteration",
                                "x Value",
                                "f(x) Value"};
        // Initalize table data array
        String[][] tableData = new String[xValues.length][fxValues.length];
        // Add data to table array
        int IterationColumn = 0;
        int xValuesColumn = 1;
        int fxValuesColumn = 2;
        for (int i = 0; i < xValues.length; i++) {
            tableData[i][IterationColumn] = Integer.toString(xValues.length - i);
            tableData[i][xValuesColumn] = Double.toString(xValues[i]);
            tableData[i][fxValuesColumn] = Double.toString(fxValues[i]);
           }
        // Create and return table
        JTable output = new JTable(tableData, columnNames);
        return  output;
        }

    public void combineGraphAndTable(String title, double[]xValues, double[]yValues) {
        // Add data to the chart, label the axis and add a title
        currentChart.addSeries("Function Series" ,xValues, yValues);
        currentChart.setXAxisTitle("x");
        currentChart.setYAxisTitle("f(x)");
        currentChart.setTitle(title);

        // Create a table to show detailed values
        JTable dataTable = createTable(xValues, yValues);

        // Add the table to a scrollpane to ensure titles of columns are visible
        tablePanel.add(new JScrollPane(dataTable));

        // New frame set up
        dataFrame.add(chartPanel, BorderLayout.NORTH);
        dataFrame.add(tablePanel, BorderLayout.SOUTH);
        dataFrame.pack();
        dataFrame.setVisible(true);
    }
}
