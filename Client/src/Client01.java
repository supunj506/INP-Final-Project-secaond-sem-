/*
 * @author : @MJ
 * Date    : 6/7/2023
 * Time    : 11:25 AM
 * Project : App1
 * Created by IntelliJ IDEA.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client01 extends Application {
    public static void main(String[] args) {
        launch(args);


//        Socket socket;
//        String sendMassage="";
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//        try {
//            socket = new Socket("localhost",5000);
//            DataOutputStream outputData = new DataOutputStream(socket.getOutputStream());
//            DataInputStream inputData = new DataInputStream(socket.getInputStream());
//            while(!sendMassage.equals("exit")){
//                sendMassage = bufferedReader.readLine();
//                System.out.println(inputData.readUTF());
//                outputData.writeUTF(sendMassage);
//                System.out.println();
//                outputData.flush();
//
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/ClientLoginForm.fxml")))));
        primaryStage.show();
    }
}
