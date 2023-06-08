/*
 * @author : @MJ
 * Date    : 6/7/2023
 * Time    : 5:37 PM
 * Project : App1
 * Created by IntelliJ IDEA.
 */

package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ChatRoomFormController extends Thread implements Initializable {
    public Label lblUserName;
    public TextField txtMassage;
    public VBox vBox;
    public Label lblTime;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    String massage = "";
    FileChooser fileChooser;
    File filePath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectToSocket();
        lblUserName.setText(ClientLoginFormController.userName);
        setTime();

    }
    @Override
    public void run() {
        try {
            while (true) {

                massage = reader.readLine();
                String[] temp1 = massage.split(" ");
                String userName = temp1[0];
                String isImg = temp1[1];
//            to get massage only

                String[] temp2 = massage.split(" ");
                String fullMassage = "";
                for (int i = 0; i < temp2.length - 1; i++) {
                    fullMassage += temp2[i + 1] + " ";
                }

                if (isImg.equals("img")) {
                    System.out.println(isImg + "  yesss");
                    String[] temp3 = fullMassage.split(" ");
                    String path = "";
                    for (int i = 1; i < temp3.length; i++) {
                        path += temp3[i];
                    }
                    System.out.println(path);

                    File file = new File(path);
                    Image image = new Image(file.toURI().toString());
                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(160);
                    imageView.setFitWidth(160);

                    HBox hBox = new HBox(20);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);

                    if (!userName.equalsIgnoreCase(ClientLoginFormController.userName)) {

                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        Text text1 = new Text( userName + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me");
                        hBox.getChildren().add(text1);
                    }

                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));

                } else {

                    Text text = new Text(fullMassage);
                    TextFlow receiverTextFlow = new TextFlow();

                    if (!userName.equals(ClientLoginFormController.userName + ":")) {
                        Text textName = new Text(userName);
                        receiverTextFlow.getChildren().add(textName);
                    }
                    receiverTextFlow.getChildren().add(text);

                    TextFlow sendTextFlow = new TextFlow(receiverTextFlow);
                    sendTextFlow.setMaxWidth(200);

                    HBox hBox = new HBox(100);
                    hBox.setMinHeight(50);
                    hBox.setMaxHeight(50);
                    hBox.setPrefHeight(50);
                    hBox.setFillHeight(false);

                    vBox.setAlignment(Pos.TOP_LEFT);

                    if (!userName.equals(ClientLoginFormController.userName + ":")) {
//                System.out.println("not owner");
                        receiverTextFlow.getStyleClass().add("textFlowReceiver");
                        sendTextFlow.getStyleClass().add("textFlowReceiver");
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(sendTextFlow);
                    } else {
//                System.out.println("owner");
                        text.setFill(Color.WHITE);
                        receiverTextFlow.getStyleClass().add("textFlowSend");
                        sendTextFlow.getStyleClass().add("textFlowSend");
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(sendTextFlow);
                    }

                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTime() {
        Timeline clock =new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime localTime=LocalTime.now();
            lblTime.setText(localTime.getHour()+":"+localTime.getMinute()+":"+localTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
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

    public void openFileOnAction(MouseEvent mouseEvent) {
        Stage stage =(Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Your Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(ClientLoginFormController.userName + " img "+filePath.getPath());
    }

    public void sendOnAction(MouseEvent mouseEvent) {

        sendMassage();
    }

    public void keySendOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            sendMassage();
        }
    }

    private void sendMassage() {
        massage = txtMassage.getText();
        if(!massage.equals("")){
            writer.println(ClientLoginFormController.userName + ": " + massage);
            txtMassage.setText("");
        }

    }

}
