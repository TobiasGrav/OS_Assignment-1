package servers;

import computations.SearchSimulator;
import utils.ResponseGenerator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SingleThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public SingleThreadedServer(int port) throws IOException {
        this.serverPort = port;
        this.serverSocket = new ServerSocket(this.serverPort);
    }

    public void run() {
        System.out.println("Server started on port 8080.");

        Socket socketConnection = null;

        while (!isStopped()) {
            try {
                socketConnection = openServerSocket();

                SearchSimulator.processClientRequest(socketConnection);
                socketConnection.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() throws IOException {
        this.isStopped = true;
        this.serverSocket.close();
    }

    private Socket openServerSocket() throws IOException {
        return this.serverSocket.accept();
    }
}