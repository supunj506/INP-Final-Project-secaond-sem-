/*
 * @author : @MJ
 * Date    : 6/7/2023
 * Time    : 10:45 PM
 * Project : App1
 * Created by IntelliJ IDEA.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManage extends Thread{
    private ArrayList<ClientManage> clients;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;


    public ClientManage(Socket socket, ArrayList<ClientManage> clients) {
        try {
            this.socket = socket;
            this.clients = clients;
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            String massage;
            while ((massage = reader.readLine()) != null) {
                if (massage.equalsIgnoreCase("exit")) {
                    break;
                }
                for (ClientManage client : clients) {
                    client.writer.println(massage);
                }
            }
        } catch (IOException e) {
            /*   e.printStackTrace();*/
        } finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                /*e.printStackTrace();*/
            }
        }
    }

}
