/*
 * @author : @MJ
 * Date    : 6/6/2023
 * Time    : 3:56 PM
 * Project : App1
 * Created by IntelliJ IDEA.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket =new ServerSocket(5000);

            while (true){
                System.out.println("Waiting for Clients...");
                socket=serverSocket.accept();
                DataInputStream inputData = new DataInputStream(socket.getInputStream());
                String inputMassage = inputData.readUTF();
                DataOutputStream outputData = new DataOutputStream(socket.getOutputStream());
                outputData.writeUTF(inputMassage);
                outputData.flush();

                System.out.println("Client connect successfully...!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
