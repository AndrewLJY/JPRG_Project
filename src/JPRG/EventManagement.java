/*
 * Class: DIT/FT/1B/03
 * Admission Number: p2014962
 * Name: Loke Jing Yang
 */
package JPRG;

import java.util.*;
import javax.swing.JOptionPane;

public class EventManagement {

    // Initialization of necessary variables
    File_IO fio = new File_IO();

    ArrayList eventList = fio.createEvents();

    ArrayList eventnameList = fio.createEventnames();

    ArrayList eventfeesList = fio.createEventfees();

    // Headers for displaying events
    String defformat = String.format("%-40s %-55s %-50s %-50s %s", "S/N", "Name", "Organizer", "Date/Time", "Fees($)") + "\n\n";

    // Methods
    public String createEvent() {
        int count = 1;
        String createdEvent = "";
        for (int i = 0; i < eventList.size(); i++) {
            Event fE = (Event) eventList.get(i);
            createdEvent += String.format("%-40s %-57s %-50s %-52s %s %n", count, fE.name, fE.organizer, fE.datetime, fE.fees);
            count++;
        }
        return createdEvent;
    }// END OF CREATE EVENT

    public String displayEvent(String eventstodisp) {
        String eventdisplay = defformat + eventstodisp;
        return eventdisplay;
    }// END OF DISPLAYEVENT

    public String addEvent(String ename, String eorganizer, String efees, String edatetime, String eventtype, String passcode) {
        boolean nameValid = false;
        String eventname = "";
        String result = "none";

        while (!nameValid) {
            boolean checkName = false;
            eventname = ename;
            for (int i = 0; i < eventnameList.size(); i++) {
                if (eventnameList.get(i).equals(eventname)) {
                    checkName = true;
                }
                if (i + 1 == eventnameList.size()) {
                    if (checkName) {
                        result = "This event name already exists, please try again.";
                        return result;
                    } else {
                        nameValid = true;
                    }
                }
            }
        }// END OF NAME INPUT

        String eventorganizer = eorganizer;
        // END FOR ORGANIZER NAME INPUT

        String eventdatetime = edatetime;
        // END OF DATE INPUT

        boolean feesValid = false;
        double valueFees = 0;

        while (!feesValid) {
            String eventfees = efees;

            if (isNumeric(eventfees)) {
                valueFees = Double.parseDouble(eventfees);
                feesValid = true;
            } else {
                result = "Invalid Fees! Please enter a number.";
                return result;
            }
        }// END OF FEES INPUT

        String privatepasscode = passcode;

        eventnameList.add(eventname);
        eventfeesList.add(valueFees);
        if (eventtype.equals("Public")) {
            eventList.add(new PublicEvent(eventname, eventorganizer, valueFees, eventdatetime, eventtype));
        } else {
            eventList.add(new PrivateEvent(eventname, eventorganizer, valueFees, eventdatetime, eventtype, privatepasscode));
        }

        return result;
    }// END OF ADDEVENT

    public String deleteEvent(String deletedevent) {
        boolean found = false;
        boolean checkAll;
        String result;

        while (!found) {
            checkAll = false;
            String deleteName = deletedevent;
            for (int i = 0; i < eventnameList.size(); i++) {
                if (eventnameList.get(i).equals(deleteName)) {
                    eventList.remove(i);
                    eventfeesList.remove(i);
                    eventnameList.remove(i);
                    found = true;
                }
            }
            checkAll = true;
            if (checkAll) {
                if (found) {
                    result = "none";
                    return result;
                } else {
                    System.out.println(found);
                    result = "No Event Found.";
                    return result;
                }
            }
        }
        return null;
    }// END OF DELETEEVENT

    public void exitProgram() {
        fio.createEventsObject(eventList, eventnameList, eventfeesList);
        System.exit(0);
    }// END OF EXITPROGRAM

    public String searchEventname(String searchname) {
        String searchnameformat = String.format("%-55s %-50s %-50s %s", "Name", "Organizer", "Date/Time", "Fees($)") + "\n\n";
        String foundEvent = "";
        String result;

        boolean found = false;
        while (!found) {
            String searchName = searchname;
            Event fE = (Event) eventList.get(0);
            for (int i = 0; i < eventnameList.size(); i++) {
                if (eventnameList.get(i).equals(searchName)) {
                    fE = (Event) eventList.get(i);
                    foundEvent += String.format("%-57s %-50s %-52s %s %n", fE.name, fE.organizer, fE.datetime, fE.fees);
                    found = true;
                }
                if (i + 1 == eventnameList.size()) {
                    if (found) {
                        foundEvent += "\n\n" + fE.displayMessage();
                        result = searchnameformat + foundEvent;
                        return result;
                    } else {
                        result = "No Event Found.";
                        return result;
                    }
                }
            }
        }
        return null;
    }// END OF SEARCHEVENTNAME

    public String searchEventfees(String searchfees) {
        String foundEvent = "";
        int count = 1;
        double valueFees;
        String result;

        boolean found = false;
        while (!found) {
            String searchFees = searchfees;
            if (isNumeric(searchFees)) {
                valueFees = Double.parseDouble(searchFees);
                for (int i = 0; i < eventfeesList.size(); i++) {
                    Double fees = (double) eventfeesList.get(i);
                    if (fees <= valueFees) {
                        Event fE = (Event) eventList.get(i);
                        foundEvent += String.format("%-40s %-57s %-50s %-52s %s %n", count, fE.name, fE.organizer, fE.datetime, fE.fees);
                        count++;
                        found = true;
                    }
                    if (i + 1 == eventfeesList.size()) {
                        if (found) {
                            result = defformat + foundEvent;
                            return result;
                        } else {
                            result = "No Event Found.";
                            return result;
                        }
                    }
                }
            } else {
                result = "Please enter a number.";
                return result;
            }
        }
        return null;
    }// END OF SEARCHEVENTFEES

    // Variables only the method "registerEvent"
    private String allRegistered = "";
    private double totalCost = 0;
    ArrayList registereddateList = new ArrayList();

    public String registerEvent(String registername) {
        boolean found = false;
        boolean conflictDate = false;
        String result;

        while (!found) {
            String registerName = registername;

            for (int i = 0; i < eventnameList.size(); i++) {
                if (eventnameList.get(i).equals(registerName)) {
                    Event fE = (Event) eventList.get(i);
                    if (registereddateList.contains(fE.datetime)) {
                        conflictDate = true;
                    } else {
                        found = true;
                        if (fE.type.equals("Private")) {
                            PrivateEvent PfE = (PrivateEvent) eventList.get(i);
                            String enteredpasscode = JOptionPane.showInputDialog(null,
                                    "Enter the passcode to register the event:", "Passcode Input",
                                    JOptionPane.QUESTION_MESSAGE);
                            if (PfE.getPasscode().equals(enteredpasscode)) {
                                JOptionPane.showMessageDialog(null, "The passcode is correct.",
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                                allRegistered += "\n* " + registerName;
                                totalCost += (double) eventfeesList.get(i);
                                registereddateList.add(fE.datetime);
                            } else {
                                JOptionPane.showMessageDialog(null, "The passcode is incorrect. Please try again.",
                                        "Error", JOptionPane.INFORMATION_MESSAGE);
                                return "";
                            }
                        } else {
                            allRegistered += "\n* " + registerName;
                            totalCost += (double) eventfeesList.get(i);
                            registereddateList.add(fE.datetime);
                        }
                    }
                }
                if (i + 1 == eventnameList.size()) {
                    if (conflictDate) {
                        result = "This event's date conflicts with another event. Please enter another event.";
                        return result;
                    }
                    if (!found) {
                        result = "No Event Found.";
                        return result;
                    } else {
                        result = "You have registered for the following event(s): " + allRegistered + "\n\nYour total cost is $" + totalCost;
                        return result;
                    }
                }
            }
        }
        return null;
    }// END OF REGISTER EVENT

    // Validation method to check for non-numeric inputs
    public boolean isNumeric(String valuetoCheck) {
        try {
            double d = Double.parseDouble(valuetoCheck);
        } catch (NumberFormatException nfe) {
            return false;
        } catch (NullPointerException npe) {
            exitProgram();
        }
        return true;
    }// END OF ISNUMERIC

}// END OF EVENTMANAGEMENT CLASS
