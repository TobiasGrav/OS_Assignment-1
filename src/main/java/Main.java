import servers.MultiThreadedServer;
import servers.SingleThreadedServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

class Main implements Runnable{

    private static SingleThreadedServer singleThreadedServer = null;
    private static MultiThreadedServer multiThreadedServer = null;

    public static void main(String[] args) throws IOException {

        startGUI(); // GUI when starting the program

        Thread inputScanner = new Thread(new Main()); // separate thread which checks for a key input to restart the server
        inputScanner.start();

        if(singleThreadedServer != null) {
            singleThreadedServer.run();
        } else {
            multiThreadedServer.run();
        }
    }

    @Override
    public void run() {
        Scanner inputScanner = new Scanner(System.in);  // Create a Scanner object
        String response = inputScanner.nextLine();  // Read user input
        if (response.toLowerCase().equals("q")) {
            try {
                // attempts to stop the server
                if(singleThreadedServer != null) {
                    singleThreadedServer.stop();
                } else {
                    multiThreadedServer.stop();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("Wrong input! \n Input Q to stop the server.");
        }
    }

    public static void startGUI() throws IOException {

        System.out.println("""
                This program was created to demonstrate the use of threads when creating a server. (By group-18)
                Choose a server type for demonstration.
                Input [M] for multi-thread or [S] for single-threaded server.
                waiting for input...
                """);

        boolean validInput = false;
        while(!validInput) {
            Scanner inputScanner = new Scanner(System.in);  // Create a Scanner object
            String response = inputScanner.nextLine();  // Read user input
            if(response.toLowerCase().equals("s")) {
                singleThreadedServer = new SingleThreadedServer(8080);
                validInput = true;
            } else if(response.toLowerCase().equals("m")) {
                multiThreadedServer = new MultiThreadedServer(8080);
                validInput = true;
            } else {
                System.out.println("Not a valid input! \n Input [M] for multi-thread and [S] for single-threaded server.");
            }
        }

    }
}