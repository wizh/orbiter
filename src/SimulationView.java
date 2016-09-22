import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.lang.NumberFormatException;

import java.io.IOException;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JOptionPane;

import javax.swing.event.ChangeEvent;

/**
* Displays the view that handles options for new simulations.
* Also holds the drawing of the simulation.z
* @author Mathias Fleig Mortensen
*/
public class SimulationView extends JFrame implements ItemListener
{
    /**
    * Possible delta values for new simulation.
    */
    private static final String[] deltas = {"1.0", "0.5","0.05", "0.001"};
    private SimulationModel simulationModel;
    private SimulationViewDrawer viewDrawer;
    private List<String> planetsToSimulate;
    private JComboBox<String> deltaCombo;
    private JTextField lifetimeTextField;
    private JButton newSimulationButton;
    private JSlider speedSlider;
    private JCheckBox mercuryBox;
    private JCheckBox venusBox;
    private JCheckBox earthBox;
    private JCheckBox marsBox;
    private JCheckBox jupiterBox;
    private JCheckBox saturnBox;
    private JCheckBox uranusBox;
    private JCheckBox neptuneBox;
    private JCheckBox plutoBox;

    /**
    * Main constructor constructing all of the UI elements.
    * @param simulationModel model to be used by the view
    * @throws IOException if a file is not present
    */
    SimulationView(SimulationModel simulationModel) throws IOException
    {
        this.simulationModel = simulationModel;
        this.planetsToSimulate = new ArrayList<String>();
        this.planetsToSimulate.add("Mercury");
        this.planetsToSimulate.add("Venus");
        this.planetsToSimulate.add("Earth");
        this.planetsToSimulate.add("Mars");
        this.planetsToSimulate.add("Jupiter");
        this.planetsToSimulate.add("Saturn");
        this.planetsToSimulate.add("Uranus");
        this.planetsToSimulate.add("Neptune");
        this.planetsToSimulate.add("Pluto");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 0);
        this.setSize((int)Toolkit.getDefaultToolkit().getScreenSize()
                     .getWidth() - 300,
                     (int)Toolkit.getDefaultToolkit().getScreenSize()
                     .getHeight());
        this.setResizable(false);

        JPanel toolbar = new JPanel(new BorderLayout());

        mercuryBox = new JCheckBox("Mercury");
        venusBox = new JCheckBox("Venus");
        earthBox = new JCheckBox("Earth");
        marsBox = new JCheckBox("Mars");
        jupiterBox = new JCheckBox("Jupiter");
        saturnBox = new JCheckBox("Saturn");
        uranusBox = new JCheckBox("Uranus");
        neptuneBox = new JCheckBox("Neptune");
        plutoBox = new JCheckBox("Pluto");

        mercuryBox.setSelected(true);
        venusBox.setSelected(true);
        earthBox.setSelected(true);
        marsBox.setSelected(true);
        jupiterBox.setSelected(true);
        saturnBox.setSelected(true);
        uranusBox.setSelected(true);
        neptuneBox.setSelected(true);
        plutoBox.setSelected(true);

        mercuryBox.addItemListener(this);
        venusBox.addItemListener(this);
        earthBox.addItemListener(this);
        marsBox.addItemListener(this);
        jupiterBox.addItemListener(this);
        saturnBox.addItemListener(this);
        uranusBox.addItemListener(this);
        neptuneBox.addItemListener(this);
        plutoBox.addItemListener(this);

        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.add(mercuryBox);
        checkPanel.add(venusBox);
        checkPanel.add(earthBox);
        checkPanel.add(marsBox);
        checkPanel.add(jupiterBox);
        checkPanel.add(saturnBox);
        checkPanel.add(uranusBox);
        checkPanel.add(neptuneBox);
        checkPanel.add(plutoBox);

        JPanel leftPanel = new JPanel(new GridLayout(1, 2));
        JPanel leftTextPanel = new JPanel(new GridBagLayout());

        leftPanel.add(checkPanel);

        JLabel lifetimeLabel = new JLabel("<html>Number of days<br>" +
            "for simulation.<br> 0 assumes infinite.</html>", JLabel.CENTER);
        lifetimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftTextPanel.add(lifetimeLabel);

        lifetimeTextField = new JTextField("0");
        lifetimeTextField.setPreferredSize(new Dimension(150, 20));
        leftTextPanel.add(lifetimeTextField);

        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel rightPanel1 = new JPanel(new GridBagLayout());
        JPanel rightPanel2 = new JPanel(new GridLayout(2, 1));

        rightPanel1.add(lifetimeLabel);
        rightPanel1.add(lifetimeTextField);
        rightPanel2.add(rightPanel1);

        this.newSimulationButton = new JButton("New Simulation");
        rightPanel2.add(newSimulationButton);

        rightPanel.add(rightPanel2, BorderLayout.CENTER);

        JPanel sliderPanel = new JPanel(new GridLayout(4, 1));
        this.deltaCombo = new JComboBox<String>(deltas);
        this.deltaCombo.setSelectedIndex(2);

        JLabel deltaLabel = new JLabel("∆t in days", JLabel.CENTER);
        deltaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sliderPanel.add(deltaLabel, BorderLayout.NORTH);
        sliderPanel.add(deltaCombo);

        JLabel speedSliderLabel =
        new JLabel("Speed of simulation in days per second (or as fast as" +
                   "possible with too low delta)", JLabel.CENTER);
        speedSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 50, 25);
        speedSlider.setPaintLabels(true);

        Hashtable<Integer, JLabel> speedLabelTable =
        new Hashtable<Integer, JLabel>();
        speedLabelTable.put(new Integer(1), new JLabel("1"));

        for (int i = 1; i < 10; i++) {
            speedLabelTable.put(new Integer(i * 5),
                                new JLabel(Integer.toString(i * 5)));
        }

        speedLabelTable.put(new Integer(50), new JLabel("50"));
        speedSlider.setLabelTable(speedLabelTable);

        sliderPanel.add(speedSliderLabel);
        sliderPanel.add(speedSlider);

        toolbar.add(leftPanel, BorderLayout.WEST);
        toolbar.add(rightPanel, BorderLayout.EAST);
        toolbar.add(sliderPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        this.viewDrawer = new SimulationViewDrawer(simulationModel);
        this.add(viewDrawer, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
    * Gets the list of planets to simulate.
    * @return the list of planets to simulate
    */
    public List<String> getPlanetsToSimulate()
    {
        return this.planetsToSimulate;
    }

    /**
    * Gets the desired lifetime of the new simulation.
    * @return the desired lifetime of the new simulation
    */
    public int getLifetimeFieldValue()
    {
        try {
            return Integer.parseInt(lifetimeTextField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(new JFrame(),
                                          "Invalid input, infinite assumed.",
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }

        return 0;
    }

    /**
    * Gets the desired deltavalue of the new simulation.
    * @return the desired deltavalue of the new simulation
    */
    public double getDeltaComboValue()
    {
        return Double.parseDouble((String)deltaCombo.getSelectedItem());
    }

    /**
    * Gets the desired speed of the new simulation.
    * @return the desired speed of the new simulation
    */
    public double getSpeedSliderValue()
    {
        return speedSlider.getValue();
    }

    /**
    * Triggered when a checkbox is checked or unchecked.
    * It's fint that the view handles this since it only
    * changes the state of the view.
    */
    public void itemStateChanged(ItemEvent e)
    {
        // Gets name of selected checkbox
        String planet = ((JCheckBox) e.getItemSelectable()).getActionCommand();

        if (e.getStateChange() == ItemEvent.DESELECTED)
            this.planetsToSimulate.removeAll(Collections.singleton(planet));
        else
            this.planetsToSimulate.add(planet);
    }

    /**
    * Gets the view that draws the simulation.
    * @return the view that draws the simulation.
    */
    public SimulationViewDrawer getViewDrawer()
    {
        return viewDrawer;
    }

    /**
    * Adds a new simulation listener to the "New Simulation" button.
    * Used by the controller.
    */
    public void addNewSimulationListener(ActionListener newSimulationListener)
    {
        newSimulationButton.addActionListener(newSimulationListener);
    }
}