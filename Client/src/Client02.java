/*
 * @author : @MJ
 * Date    : 6/7/2023
 * Time    : 11:11 PM
 * Project : App1
 * Created by IntelliJ IDEA.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Client02 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/ClientLoginForm.fxml")))));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Play Tech Pvt Ltd ");
        primaryStage.show();
    }
}
