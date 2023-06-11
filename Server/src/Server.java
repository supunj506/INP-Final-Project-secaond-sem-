/*
 * @author : @MJ
 * Date    : 6/6/2023
 * Time    : 3:56 PM
 * Project : App1
 * Created by IntelliJ IDEA.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final static ArrayList<ClientManage> clients = new ArrayList<ClientManage>();

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(5000);
            while (true) {
                System.out.println("Waiting for clients...");
                socket = serverSocket.accept();
                System.out.println("Connected Successfully...");
                ClientManage clientManage = new ClientManage(socket, clients);
                clients.add(clientManage);
                clientManage.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
