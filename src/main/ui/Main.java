package ui;



import model.Event;
import model.EventLog;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    static class ForceClosedMessage extends Thread {

        ArrayList events = new ArrayList<>();

        private void printLog(EventLog el) {
            for (Event next : el) {
                events.add(next.toString());
            }
        }

        public void run() {
            printLog(EventLog.getInstance());
            System.out.println(events);
        }
    }

    public static void main(String[] args) {
//        try {
//            new StudentIDCardApp()3;
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
        new GUI();
        Runtime.getRuntime().addShutdownHook(new ForceClosedMessage());
    }

}
