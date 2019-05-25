package vk;

import com.vk.api.sdk.client.TransportClient;
//import com.vk.api.sdk.exceptions.ApiException;
//import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.beans.value.ObservableValue;

//import org.apache.logging.log4j.LogManager;

import com.vk.api.sdk.client.VkApiClient;

public class VkAuthController {

    @FXML
    private WebView webView;

    private WebEngine engine;
    private Integer appId = 6996985;

    private String redirectUrl = "https://oauth.vk.com/blank.html";
   // private String vkAuthUrl = "https://oauth.vk.com/authorize/?client_id="+ appId +"&redirect_uri="+ redirectUrl +"&display=mobile&scope=email,messages";
    private String vkAuthUrl = "https://oauth.vk.com/authorize/?client_id="+ appId +"&redirect_uri="+ redirectUrl +"&display=mobile&scope=email,messages";

    private String tokenUrl;

    private VkApiClient vk;
    // &scope=email

    public void initialize()
    {
        try {
            engine = webView.getEngine();
            engine.load(vkAuthUrl);

            engine.locationProperty().addListener(new ChangeListener<String>(){
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if(newValue.startsWith(redirectUrl)){
                        System.out.println(newValue);
                        tokenUrl = newValue;
                        //primaryStage.close();
                        String code = tokenUrl.substring(tokenUrl.lastIndexOf("=") + 1);
                        System.out.println(code);
                        App.setVkAuthCode(code);
                        getAccessToken();
                    }
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAccessToken()
    {
        String code = App.getVkAuthCode();
        if (code.length() > 0)
        {
            try {
                TransportClient transportClient = HttpTransportClient.getInstance();
                vk = new VkApiClient(transportClient);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                UserAuthResponse authResponse = vk.oAuth()
                        .userAuthorizationCodeFlow(appId, secretKey, redirectUrl, code)
                        .execute();

                System.out.println(authResponse.getAccessToken());
                App.setVkAccessToken(authResponse.getAccessToken()); // access token получен
                App.setVkUserId(authResponse.getUserId());

                if (authResponse.getUserId() > 0)
                    App.setRoot("primary"); // переключаемся в окно с действиями

            } catch (Exception e) {
                e.printStackTrace();
            }
        }




//
//        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
    }




}
