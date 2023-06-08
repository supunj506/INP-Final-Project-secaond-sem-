/*
 * @author : @MJ
 * Date    : 6/7/2023
 * Time    : 5:37 PM
 * Project : App1
 * Created by IntelliJ IDEA.
 */

package controller;


import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatRoomFormController extends Thread implements Initializable {
    public Label lblUserName;
    public TextField txtMassage;
    public VBox vBox;


    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    String massage = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectToSocket();
        lblUserName.setText(ClientLoginFormController.userName);


    }


    private void connectToSocket() {
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run(){
        try {
        while (true){

            massage = reader.readLine();
            String[] temp1 = massage.split(" ");
            String userName = temp1[0];
//            to get massage only

            String [] temp2 = massage.split(" ");
            String fullMassage = "" ;
            for(int i = 0 ; i < temp2.length - 1; i++ ){
                fullMassage += temp2[i + 1] + " " ;
            }

            Text text =new Text(fullMassage);
            text.getStyleClass().add("textStyle");
            TextFlow receiverTextFlow = new TextFlow();

            if (!userName.equals(ClientLoginFormController.userName+":")){
                Text textName = new Text(userName);
                receiverTextFlow.getChildren().add(textName);
            }
            receiverTextFlow.getChildren().add(text);

            TextFlow sendTextFlow = new TextFlow(receiverTextFlow);
            sendTextFlow.setMaxWidth(200);

            HBox hBox = new HBox(20);
            hBox.setMinHeight(50);
            hBox.setMaxHeight(50);
            hBox.setPrefHeight(50);
            hBox.setFillHeight(false);

            if(!userName.equals(ClientLoginFormController.userName+":")){
//                System.out.println("not owner");
                receiverTextFlow.getStyleClass().add("textFlowReceiver");
                sendTextFlow.getStyleClass().add("textFlowReceiver");
                hBox.setAlignment(Pos.CENTER_LEFT);
                hBox.getChildren().add(sendTextFlow);
            }else{
//                System.out.println("owner");
                receiverTextFlow.getStyleClass().add("textFlowSend");
                sendTextFlow.getStyleClass().add("textFlowSend");
                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                hBox.getChildren().add(sendTextFlow);
            }

            Platform.runLater(() -> vBox.getChildren().addAll(hBox));

        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void openImojeOnAction(MouseEvent mouseEvent) {

    }

    public void openFileOnAction(MouseEvent mouseEvent) {

    }

    public void sendOnAction(MouseEvent mouseEvent) {
        massage = txtMassage.getText();
        writer.println( ClientLoginFormController.userName+": " + massage);
        txtMassage.setText("");

    }



}
