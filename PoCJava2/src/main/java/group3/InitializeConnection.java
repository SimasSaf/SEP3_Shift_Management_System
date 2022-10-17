package group3;

import group3.model.TransferObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

@Component
public class InitializeConnection
{

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Socket clientSocket;


    public InitializeConnection() throws IOException, ClassNotFoundException
    {

        //hello this is test

        InetAddress host = InetAddress.getLocalHost();

        //create a client socket and connect to the server
        clientSocket = new Socket(host.getHostAddress(), 6789);


        // create an object output and input stream so we can send an object through it and receive
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

        System.out.println("obj out: " + objectOutputStream);
        System.out.println("obj in: " + objectInputStream);


        //System.out.println("Closing socket...");
        //TimeUnit.SECONDS.sleep(1);
        //close the socket on the client side
        //clientSocket.close();

    }

    public Object sendTransferObject(String command, Object object) throws IOException, ClassNotFoundException
    {

        System.out.println("Sent Command: " + command);
        System.out.println("Sent Object: " + object);
        TransferObject transferObject = new TransferObject(object, command);

        objectOutputStream.writeObject(transferObject);

        Object receivedObject = objectInputStream.readObject();

        System.out.println("Received: " + receivedObject);

        if (receivedObject instanceof TransferObject)
        {
            command = ((TransferObject) receivedObject).getCommand();
            object = ((TransferObject) receivedObject).getObject();
        }
        else
        {
            System.out.println("Received the wrong object type");
        }

        return object;

    }
}
