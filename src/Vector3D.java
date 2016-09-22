/**
* Handles vector representation and calculation.
* @author Mathias Fleig Mortensen
*/
public class Vector3D
{
    private double x;
    private double y;
    private double z;

    /**
    * The main constructor.
    * @param x the first coordinate
    * @param y the second coordinate
    * @param z the third coordinate
    */

    public Vector3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
    * Gets the first coordinate.
    * @return the first coordinate of the vector
    */
    public double getX()
    {
        return this.x;
    }

    /**
    * Gets the second coordinate.
    * @return the second coordinate of the vector
    */
    public double getY()
    {
        return this.y;
    }

    /**
    * Gets the third coordinate.
    * @return the third coordinate of the vector
    */
    public double getZ()
    {
        return this.z;
    }

    /**
    * Calculate the length of a 3D Vector.
    * @return the length of the vector
    */
    public double length()
    {
        return Math.sqrt(this.x * this.x +
                         this.y * this.y +
                         this.z * this.z);
    }

    /**
    * Adds two 3D Vectors.
    * @param vec the vector to add
    * @return the result of the addition between this vector and vec
    */
    public Vector3D add(Vector3D vec)
    {
        return new Vector3D(this.x + vec.getX(),
                            this.y + vec.getY(),
                            this.z + vec.getZ());
    }

    /**
    * Multiplies a 3D Vector by a scalar.
    * @param s the scalar to multiply with
    * @return the result of the scaling of the vector by s
    */
    public Vector3D mul(double s)
    {
        return new Vector3D(this.x * s, this.y * s, this.z * s);
    }
}