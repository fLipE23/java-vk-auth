package vk;

import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.users.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import com.vk.api.sdk.actions.*;
import javafx.stage.StageStyle;
//import sdk;



/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private double xOffset;
    private double yOffset;

    private static String authCode;
    private static String accessToken;
    private Integer appId = 6996985;

    private static ServiceActor actor = null;

    private static Integer userId;

    @Override
    public void start(Stage stage) throws IOException
    {
        scene = new Scene(loadFXML("first"));

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        });

//        final WebView view = new WebView();
//        final WebEngine engine = view.getEngine();
//        engine.load("https://ideaweb.site");

        stage.setScene(scene);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    static void setVkAuthCode(String code) {
        authCode = code;
    }
    static String getVkAuthCode() {
        return authCode;
    }
    static void setVkAccessToken(String token) {
        accessToken = token;
    }
    static void setVkUserId(Integer id) {
        userId = id;
    }

    static UserActor getActor() {
        return new UserActor(userId, accessToken);
    }

    static void setRoot(String fxml) throws IOException
    {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
//        root.setStyle("-fx-background-radius: 0;" +
//                      "-fx-background-color: rgb(45, 45, 50), rgb(60, 60, 65);" +
//                      "-fx-background-insets: 0, 0 1 1 0;");

        return root;
    }

    public static void main(String[] args) {
        launch();
    }

}