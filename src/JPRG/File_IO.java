/*
 * Class: DIT/FT/1B/03
 * Admission Number: p2014962
 * Name: Loke Jing Yang
 */
package JPRG;

import java.io.*;
import java.util.*;

public class File_IO {

    // Initialization of necessary variables
    Event e = new Event();
    ArrayList eventList = new ArrayList(Arrays.asList());
    ArrayList eventnameList = new ArrayList(Arrays.asList());
    ArrayList eventfeesList = new ArrayList(Arrays.asList());

    // Methods
    // Used to read the event's data to file
    public ArrayList createEvents() {
        File f = new File("events.dat");
        BufferedReader br;
        String s;
        String[] events;
        try {
            if (f.exists()) {
                System.out.println("Object file already exists.");
                ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(f));
                ArrayList eventList = new ArrayList();
                eventList = (ArrayList) inStream.readObject();
                inStream.close();
                System.out.println("Reading object file done.");
                return eventList;
            } else {
                System.out.println("Object file does not exists. So, reading from \" events.txt \".");
                br = new BufferedReader(new FileReader("events.txt"));
                while ((s = br.readLine()) != null) {
                    events = s.split(";", -2);
                    if (events[4].equals("Public")) {
                        eventList.add(new PublicEvent(events[0], events[1], Double.parseDouble(events[2]), String.valueOf(events[3]), events[4]));
                    } else {
                        eventList.add(new PrivateEvent(events[0], events[1], Double.parseDouble(events[2]), String.valueOf(events[3]), events[4], events[5]));
                    }
                }
                br.close();
                return eventList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Not Found");
        }
        return null;
    }

    // Used to read the event's names data to file
    public ArrayList createEventnames() {
        File f = new File("eventsname.dat");
        BufferedReader br;
        String s;
        String[] events;
        try {
            if (f.exists()) {
                System.out.println("Name file already exists.");
                ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(f));
                ArrayList eventnameList = new ArrayList();
                eventnameList = (ArrayList) inStream.readObject();
                inStream.close();
                System.out.println("Reading name file done.");
                return eventnameList;
            } else {
                System.out.println("Name file does not exists. So, reading from \" events.txt \".");
                br = new BufferedReader(new FileReader("events.txt"));
                while ((s = br.readLine()) != null) {
                    events = s.split(";", -2);
                    eventnameList.add(events[0]);
                }
                br.close();
                return eventnameList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Not Found");
        }
        return null;
    }

    // Used to read the event's fees data to file
    public ArrayList createEventfees() {
        File f = new File("eventsfees.dat");
        BufferedReader br;
        String s;
        String[] events;
        try {
            if (f.exists()) {
                System.out.println("Fees file already exists.");
                ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(f));
                ArrayList eventfeesList = new ArrayList();
                eventfeesList = (ArrayList) inStream.readObject();
                inStream.close();
                System.out.println("Reading fees file done.");
                return eventfeesList;
            } else {
                System.out.println("Fees file does not exists. So, reading from \" events.txt \".");
                br = new BufferedReader(new FileReader("events.txt"));
                while ((s = br.readLine()) != null) {
                    events = s.split(";", -2);
                    eventfeesList.add(Double.parseDouble(events[2]));
                }
                br.close();
                return eventfeesList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Not Found");
        }
        return null;
    }

    // Used to write the event's data to file
    public void createEventsObject(ArrayList savedevents, ArrayList savednameevents, ArrayList savedfeesList) {
        try {
            File f = new File("events.dat");
            File fn = new File("eventsname.dat");
            File ff = new File("eventsfees.dat");
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("Objects file already exists. So no need to create file.");
            }
            if (fn.createNewFile()) {
                System.out.println("File created: " + fn.getName());
            } else {
                System.out.println("Name file already exists. So no need to create file.");
            }
            if (ff.createNewFile()) {
                System.out.println("File created: " + ff.getName());
            } else {
                System.out.println("Fees file already exists. So no need to create file.");
            }
            ObjectOutputStream objectoutStream = new ObjectOutputStream(new FileOutputStream(f));
            objectoutStream.writeObject(savedevents);
            objectoutStream.close();
            System.out.println("Event objects saved.");

            ObjectOutputStream nameoutStream = new ObjectOutputStream(new FileOutputStream(fn));
            nameoutStream.writeObject(savednameevents);
            nameoutStream.close();
            System.out.println("Event name objects saved.");

            ObjectOutputStream feesoutStream = new ObjectOutputStream(new FileOutputStream(ff));
            feesoutStream.writeObject(savedfeesList);
            feesoutStream.close();
            System.out.println("Event fees objects saved.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
