import java.util.HashMap;

import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;

import javax.swing.event.ChangeListener;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;

/**
* Responsible for drawing the simulation to the screen.
* @author Mathias Fleig Mortensen
*/
public class SimulationViewDrawer extends JPanel
{
    /**
    * Set the drawing x offset to be the middle of the drawing space.
    */
    private static final int XOFFSET =
    (int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 300) / 2);

    /**
    * Set the drawing y offset to be the middle of the drawing space.
    */
    private static final int YOFFSET =
    (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 230) / 2);

    /**
    * Set the maximum upscaling of the drawing to be 250 times
    * the original coordinate values.
    */
    private static final int MAXSCALE = 250;

    /**
    * Set the minimum upscaling of the drawing to be 5 times
    * the original coordinate valuess.
    */
    private static final int MINSCALE = 5;

    /**
    * Set the initial scaling to be 150 times
    * the original coordinate values.
    */
    private static final int SCALE_INIT = 150;
    private int xscale;
    private int yscale;
    private SimulationModel simulationModel;
    private JLabel dateLabel;
    private JLabel zoomLabel;
    private JSlider zoomSlider;
    private HashMap<String, Image> images;

    /**
    * Main constructor taking a model.
    * @param simulationModel the model to get data from
    * @throws IOException if the necessary files are not present
    */
    SimulationViewDrawer(SimulationModel simulationModel)
    throws IOException
    {
        this.simulationModel = simulationModel;

        this.xscale = SCALE_INIT;
        this.yscale = SCALE_INIT;

        setOpaque(true);

        this.zoomLabel = new JLabel("Zoom in or out");
        this.zoomLabel.setForeground(Color.WHITE);
        this.add(zoomLabel);

        zoomSlider = new JSlider(JSlider.VERTICAL,
                                 MINSCALE,
                                 MAXSCALE,
                                 SCALE_INIT);
        this.add(zoomSlider);

        this.dateLabel = new JLabel();
        this.dateLabel.setForeground(Color.WHITE);
        this.add(dateLabel);

        // Initialize images
        this.images = new HashMap<String, Image>();
        for (String planetName : simulationModel.getCelestialNames()) {
            Image img = ImageIO.read(new File("../pics/" +
                                              planetName + ".png"));
            images.put(planetName, img);
        }
    }

    /**
    * Draw the planets to the screen by calling the repaint function.
    */
    public void paintPlanets()
    {
        this.repaint();
    }

    /**
    * Changes the zoom level
    * @param scale the sacle to zoom with
    */
    public void changeZoom(int scale)
    {
        this.xscale = scale;
        this.yscale = scale;
    }

    /**
    * Adds a listener to the zoom slider. Used by the controller.
    */
    public void addZoomListener(ChangeListener zoomListener)
    {
        zoomSlider.addChangeListener(zoomListener);
    }

    /**
    * Draws the planets to the screen.
    */
    public void paintComponent(Graphics g)
    {
        setBackground(Color.BLACK);
        Graphics2D g2D = (Graphics2D) g;
        dateLabel.setText("Date: " +
        simulationModel.getSimulation().getTime().getDateString());

        // Draw sun
        Image imgSun = images.get("Sun");
        Sun sun = simulationModel.getSimulation().getSun();
        g2D.drawImage(
            imgSun,
            (int)(sun.getPosition().getX() * xscale + XOFFSET -
                imgSun.getWidth(null) / 2.5),
            (int)(sun.getPosition().getY() * yscale + YOFFSET -
                imgSun.getHeight(null) / 2.5),
            null);

        if (!(this.simulationModel.getSimulation().getNumberOfPlanets() == 0)) {
            // Draw planets
            for (Planet planet : simulationModel.getSimulation().getPlanets()) {
                Image imgPlanet = images.get(planet.getName());
                g2D.drawImage(
                    imgPlanet,
                    (int)(planet.getPosition().getX() * xscale + XOFFSET -
                        imgPlanet.getWidth(null) / 2.5),
                    (int)(planet.getPosition().getY() * yscale + YOFFSET -
                        imgPlanet.getHeight(null) / 2.5),
                    null);
            }
        }
    }
}