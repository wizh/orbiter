import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
* Represents dates for a simulation.
* @author Mathias Fleig Mortensen
*/
public class Time
{
    /**
    * Time corresponding to the initial day of the simulation.
    */
    private static final double START_TIME = 2456293.5;
    private double ejd;
    private Date date;

    /**
    * Main constructor taking no arguments.
    */
    public Time()
    {
        this.ejd = START_TIME;

        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2013, 0, 1);
        this.date = cal.getTime();
    }

    /**
    * Gets the number of days the simulation has run.
    * @return the number of days the simulation has run
    */
    public double getDaysElapsed()
    {
        return this.ejd - START_TIME;
    }

    /**
    * Gets the string representation of the date.
    * @return the string representation of the date
    */
    public String getDateString()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.date);
    }

    /**
    * Sets the date to the next day.
    */
    public void setNextDay()
    {
        this.ejd += 1;
        this.date = new Date(this.date.getTime() + 24*60*60*1000);
    }

    /**
    * Determines if it is the first day of the month of whole 2013 or
    * January of 2014. Used to see if the state of the simulation
    * should be saved for comparison with the JPL data.
    * @return if state of simulation should be saved or not
    */
    public boolean shouldAddToHistory()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        return (day == 1 && year == 2013 ||
                day == 1 && year == 2014 && month == 0);
    }
}