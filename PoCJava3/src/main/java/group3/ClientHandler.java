package group3;

import org.hibernate.internal.build.AllowPrintStacktrace;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{

    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public ClientHandler(Socket socket) throws IOException
    {
        this.socket = socket;

        //set up in and out streams
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }


    @Override
    public void run(){
        try
        {
            while (true) {
                //receive
                Object receivedObject = objectInputStream.readObject();

                //test the received object
                Object sendBackObject = new TestObject().TestObject(receivedObject);


                System.out.println("Sending back: " + sendBackObject);
                //send back
                objectOutputStream.writeObject(sendBackObject);
            }
        }
        catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }
}
