/*
 * @author : @MJ
 * Date    : 6/7/2023
 * Time    : 1:55 PM
 * Project : App1
 * Created by IntelliJ IDEA.
 */

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ClientLoginFormController {
    public TextField txtUserName;

    public static String userName;

    public void btnLoginOnAction(ActionEvent actionEvent) {
        userName=txtUserName.getText();
        goToChatRoom();
    }

    private void goToChatRoom() {
        try {
            Stage stage = (Stage) txtUserName.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/ChatRoomForm.fxml")));

            stage.setScene(new Scene(root, 404, 669));
            stage.setOnCloseRequest(event -> {
                System.exit(0);
            });
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void keyLoginOnAction(KeyEvent keyEvent) {
        if(keyEvent.getCode().toString().equals("ENTER")){
            btnLoginOnAction(new ActionEvent());
        }

    }
}
