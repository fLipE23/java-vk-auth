package vk;

import java.io.IOException;
import java.util.List;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.responses.GetConversationsResponse;
import com.vk.api.sdk.queries.messages.MessagesGetConversationsQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.skin.ChoiceBoxSkin;
import javafx.scene.web.WebView;

import com.vk.api.sdk.actions.Messages;


public class PrimaryController
{
   // @FXML
   // private WebView webView;
    private UserActor actor = App.getActor();
    private static VkApiClient vk;
    private static GetConversationsResponse conversationsResponse;

    @FXML
    private ChoiceBox conversationsChoiceBox;



    @FXML
    public void initialize()
    {
        try {
            TransportClient transportClient = HttpTransportClient.getInstance();
            vk = new VkApiClient(transportClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Initialized");

        ObservableList oList = FXCollections.observableList(getConversations());

        conversationsChoiceBox.setItems(oList);

    }


    private static List getConversations()
    {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
        MessagesGetConversationsQuery query = vk.messages().getConversations(App.getActor());
        try {
            conversationsResponse = query.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conversationsResponse.getItems();
    }

}
