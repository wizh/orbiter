import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
* Can't be instantiated and only holds static methods.
* Responsible for handling file reading.
* @author Mathias Fleig Mortensen
*/
public final class DataParser
{
    /**
    * Represents the number of strings to skip before reading a vector.
    */
    private static final int VECTORINDEX = 2;
    /**
    * Private constructor so that one instantiates this class.
    */
    private DataParser(){};

    /**
    * Gets the position of a celestial body for a given month.
    * @return the position of a celestial body for a given month
    * @throws IOException if there is no file for the celestial body
    */
    public static Vector3D readPosition(String celesBodyName, int month)
    throws IOException
    {
        return readVector(celesBodyName + ".txt", month, 0);
    }

    /**
    * Gets the velocity of a celestial body for a given month.
    * @return the velocity of a celestial body for a given month
    * @throws IOException if there is no file for the celestial body
    */
    public static Vector3D readVelocity(String celesBodyName, int month)
    throws IOException
    {
        return readVector(celesBodyName + ".txt", month, 3);
    }

    /**
    * Helperfunction, gets a vector from a file, given a month and an offset.
    * @return the position or velocity of a celestial body for a given month
    * @throws IOException if there is no file for the celestial body
    */
    private static Vector3D readVector(String filename, int month, int index)
    throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("../data/" +
                                                                   filename));
        String input = "";
        try {
            // Skip to the desired month
            for (int i = 0; i < month; i++)
                reader.readLine();

            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        reader.close();

        String[] strValues = input.split(",");
        List<Double> values = new ArrayList<Double>();
        for (int i = VECTORINDEX; i < VECTORINDEX + 6; i++)
            values.add(Double.valueOf(strValues[i]));

        return new Vector3D(values.get(index),
                            values.get(index + 1),
                            values.get(index + 2));
    }
}