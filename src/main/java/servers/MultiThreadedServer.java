package servers;

import computations.AsyncSearchSimulator;
import computations.SearchSimulator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public MultiThreadedServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void run() {
        System.out.println("Server started on port 8080.");
        long threadCount = 0;
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = openServerSocket();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
            }
            String threadName = "Thread-" + threadCount;
            Thread thread = new Thread(new AsyncSearchSimulator(clientSocket, threadName));
            thread.start();
            threadCount++;
        }

        System.out.println("Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() throws IOException {
        // implementation to stop the server from the main thread if needed
        this.isStopped = true;
        serverSocket.close();
    }

    private Socket openServerSocket() throws IOException {
        return this.serverSocket.accept();
    }
}
