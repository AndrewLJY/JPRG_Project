/*
 * Class: DIT/FT/1B/03
 * Admission Number: p2014962
 * Name: Loke Jing Yang
 */
package JPRG;

import java.io.*;

public class Event implements Serializable {

    // Initialization of necessary variables
    protected String name;
    protected String organizer;
    protected String datetime;
    protected double fees;
    protected String type;

    // Methods
    public Event() {
    }

    public Event(String name, String organizer, double fees, String datetime, String type) {
        this.name = name;
        this.organizer = organizer;
        this.datetime = datetime;
        this.fees = fees;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String displayMessage() {
        return "Event";
    }
}// END OF EVENT CLASS
