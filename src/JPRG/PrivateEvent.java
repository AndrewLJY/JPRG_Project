/*
 * Class: DIT/FT/1B/03
 * Admission Number: p2014962
 * Name: Loke Jing Yang
 */
package JPRG;

import java.io.*;

public class PrivateEvent extends Event implements Serializable {

    // Initialization of necessary variables
    private String passcode;

    // Methods
    public PrivateEvent() {
    }

    public PrivateEvent(String name, String organizer, double fees, String datetime, String type, String passcode) {
        super(name, organizer, fees, datetime, type);

        this.passcode = passcode;
    }

    public String getPasscode() {
        return passcode;
    }

    @Override
    public String displayMessage() {
        return "This event ,  \"" + name + "\"  , is a private event.\nPlease be mindful that you will require a special passcode to register for this event.";
    }

}
