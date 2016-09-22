import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
* Defines the TableModel used for the data tabel.
* @author Mathias Fleig Mortensen
*/
public class DataTableModel extends AbstractTableModel
{
    private List<String> dates;
    private String[] columnNames;
    private Object[][] data;

    /**
    * Main constructor.
    */
    public DataTableModel()
    {
        this.dates = new ArrayList<String>();
        this.columnNames = new String[]{"Date", "Distance to JPL (km)"};

        for (int i = 1; i <= 12; i++)
            this.dates.add("1/" + Integer.toString(i) + "/2013");
        this.dates.add("1/1/2014");

        // Table model is intialized to be empty.
        this.data = new Object[][]{{"", ""}, {"", ""}, {"", ""},
                                   {"", ""}, {"", ""}, {"", ""},
                                   {"", ""}, {"", ""}, {"", ""},
                                   {"", ""}, {"", ""}, {"", ""},
                                   {"", ""}};
    }

    /**
    * Gets the number of columns in the table.
    * This number is always 2.
    * @return number of columns in the table
    */
    public int getColumnCount()
    {
        return 2;
    }

    /**
    * Gets the number of rows in the table.
    * This number is always 13.
    * @return number of rows in the table
    */
    public int getRowCount()
    {
        return 13;
    }

    /**
    * Gets the name of a given column.
    * @return name of a given column
    */
    public String getColumnName(int col)
    {
        return this.columnNames[col];
    }

    /**
    * Gets the object at a given index.
    * @return the object at a given index
    */
    public Object getValueAt(int row, int col)
    {
        return data[row][col];
    }

    /**
    * Determine if a given cell is editable.
    * This is always false.
    * @return if a given cell is editable
    */
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }

    /**
    * Sets the value an a given index.
    */
    public void setValueAt(Object value, int row, int col)
    {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
