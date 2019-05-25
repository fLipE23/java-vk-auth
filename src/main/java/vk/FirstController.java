package vk;

import javafx.fxml.FXML;

import java.io.IOException;

public class FirstController
{

    @FXML
    public void switchToVKAuth() throws IOException
    {
        //webView.getEngine().load("https://ideaweb.site");
        //VkAuthController.setUrl("https://ideaweb.site");

        App.setRoot("vk_auth");
    }


}
