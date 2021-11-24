/*
 * Class: DIT/FT/1B/03
 * Admission Number: p2014962
 * Name: Loke Jing Yang
 */
package JPRG;

import java.io.*;

public class PublicEvent extends Event implements Serializable {

    // Methods
    public PublicEvent() {
    }

    public PublicEvent(String name, String organizer, double fees, String datetime, String type) {
        super(name, organizer, fees, datetime, type);
    }

    @Override
    public String displayMessage() {
        return "This event ,  \"" + name + "\"  , is a public event. You will NOT need any passcode to register for this event.";
    }
}
