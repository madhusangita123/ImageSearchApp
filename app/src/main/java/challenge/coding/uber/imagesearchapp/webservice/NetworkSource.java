package challenge.coding.uber.imagesearchapp.webservice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import challenge.coding.uber.imagesearchapp.AppConstants;
import challenge.coding.uber.imagesearchapp.model.SearchImageResponse;

public class NetworkSource extends RestAPI {

    public void searchImages(String searchItem, long pageNumber,final NetworkSourceListener listener){
        String searchAPI = String.format(AppConstants.searchImageAPI,searchItem,pageNumber);
        NetworkTask networkTask = new NetworkTask(searchAPI, new NetworkTaskListener() {
            @Override
            public void onSuccess(int responseCode, String response) {

                if(responseCode == HttpURLConnection.HTTP_OK && response!=null){
                    SearchImageResponse imageResponse = JsonParser.parseResponse( response );
                    listener.onSuccess( imageResponse );
                }

            }

            @Override
            public void onFailure(int responseCode, String statusMessage) {
                listener.onFailure( responseCode,statusMessage );
            }
        } );
        networkTask.execute();
    }
}
