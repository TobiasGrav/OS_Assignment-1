package computations;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;


import utils.ResponseGenerator;

public class SearchSimulator {
    public static void processClientRequest(Socket socket) throws Exception {

        BufferedReader receiver = new BufferedReader(new InputStreamReader(socket.getInputStream())); // BufferedReader which reads responses
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true); // PrintWriter which sends messages
        //System.out.println(receiver.readLine());
        writer.println(ResponseGenerator.generatorResponseHeader(128));

        long time1 = System.currentTimeMillis();
        System.out.println("Request processing started.");
        Thread.sleep(5 * 1000);
        long time2 = System.currentTimeMillis();
        System.out.println("Request process time: " + (time2 - time1) + "ms");
        writer.println(ResponseGenerator.generatorResponseHTML(time1,time2));
    }

}