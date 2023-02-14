package computations;

import utils.ResponseGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AsyncSearchSimulator implements Runnable {

    protected Socket clientSocket = null;
    protected String threadName = null;

    public AsyncSearchSimulator(Socket clientSocket, String threadName) {
        this.clientSocket = clientSocket;
        this.threadName = threadName;
    }

    public void setNameOfThread(String threadName) {
        this.threadName = threadName;
    }

    public void run() {
        try {
            BufferedReader receiver = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true); // PrintWriter which sends messages
            //System.out.println(receiver.readLine());
            writer.println(ResponseGenerator.generatorResponseHeader(128));

            long time1 = System.currentTimeMillis();
            System.out.println(threadName + " request processing started.");
            Thread.sleep(5 * 1000);
            long time2 = System.currentTimeMillis();
            System.out.println(threadName + " request process time: " + (time2 - time1) + "ms");
            writer.println(ResponseGenerator.generatorResponseHTML(time1, time2));
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}