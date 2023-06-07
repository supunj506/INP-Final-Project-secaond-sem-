/*
 * @author : @MJ
 * Date    : 6/7/2023
 * Time    : 11:25 AM
 * Project : App1
 * Created by IntelliJ IDEA.
 */

import java.io.*;
import java.net.Socket;

public class Client01 {
    public static void main(String[] args) {
        Socket socket;
        String sendMassage="";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            socket = new Socket("localhost",5000);
            DataOutputStream outputData = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputData = new DataInputStream(socket.getInputStream());
            while(!sendMassage.equals("exit")){
                sendMassage = bufferedReader.readLine();
                System.out.println(inputData.readUTF());
                outputData.writeUTF(sendMassage);
                System.out.println();
                outputData.flush();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
