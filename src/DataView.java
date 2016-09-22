import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

/**
* Displays a view with the data.
* @author Mathias Fleig Mortensen
*/
public class DataView extends JFrame
{
    private SimulationModel simulationModel;
    private List<String> dates;
    private JComboBox<String> planetCombo;
    private JTable table;

    /**
    * Main constructor configuring all JComponents.
    * @param simulationModel the simulationModel to get data from
    */
    DataView(SimulationModel simulationModel)
    {
        this.simulationModel = simulationModel;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0, 0);
        this.setSize(300, (int)Toolkit.getDefaultToolkit().
                               getScreenSize().getHeight());
        this.setResizable(false);

        this.dates = new ArrayList<String>();
        for (int i = 1; i <= 12; i++)
            this.dates.add("1/" + Integer.toString(i) + "/2013");
        this.dates.add("1/1/2014");

        this.planetCombo =
        new JComboBox<String>(this.simulationModel.getPlanetNames());

        this.planetCombo.setSelectedIndex(3);
        this.add(this.planetCombo, BorderLayout.NORTH);

        this.table = new JTable(new DataTableModel());
        this.table.setEnabled(false);
        this.table.setRowHeight(30);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        this.setVisible(true);
    }

    /**
    * Returns the name of the planet that is selected by the combobox.
    * @return the name of the planet that is selected by the combobox
    */
    public String getSelectedPlanet()
    {
        return this.planetCombo.getSelectedItem().toString();
    }

    /**
    * Clears all entries in the table.
    */
    public void clearList()
    {
        for (int i = 0; i < 13; i++) {
            table.setValueAt("", i, 0);
            table.setValueAt("", i, 1);
        }
    }

    /**
    * Updates the table with the distances to the JPL positions
    * for the selected planet in the combobox.
    * @throws PlanetDoesNotExistException if selected planet is not initialized
    */
    public void updateList() throws PlanetDoesNotExistException, IOException
    {
        try {
            List<String> positions = simulationModel.getSimulation().
                                     getHistory(getSelectedPlanet());
            for (int i = 0; i < positions.size(); i++) {
                table.setValueAt(dates.get(i), i, 0);
                table.setValueAt(positions.get(i), i, 1);
            }
        } catch (PlanetDoesNotExistException ex) {
            clearList();
        }
    }

    /**
    * Adds a listener to the combobox.
    * Used so that the controller knows when to update the data table.
    */
    public void addPlanetComboListener(ActionListener planetComboListener)
    {
        this.planetCombo.addActionListener(planetComboListener);
    }
}