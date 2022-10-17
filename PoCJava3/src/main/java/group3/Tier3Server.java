package group3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Tier3Server implements Runnable{

    @Override
    public void run()
    {
        System.out.println("\nWaiting for client connection ...\n");
        ServerSocket server = null;

        try {

            server = new ServerSocket(6789);

            while (true) {
                Socket socket = server.accept();
                System.out.println("New client connected");
                ClientHandler c = new ClientHandler(socket);
                Thread t = new Thread(c);
                System.out.println("Thread: " + t);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
