package challenge.coding.uber.imagesearchapp.webservice;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import challenge.coding.uber.imagesearchapp.AppConstants;

public class NetworkTask extends AsyncTask<Void, Void, NetworkResponse> {

    private String mURL;
    private NetworkTaskListener mListener;


    public NetworkTask( String url, NetworkTaskListener listener) {
        mURL = url;
        mListener = listener;

    }

    @Override
    protected NetworkResponse doInBackground(Void... voids) {
        NetworkResponse response = null;
        try {
            response = new RestAPI().getPhotoCollection(new URL(mURL));

        }catch (IOException e){
            response = new NetworkResponse(e.getMessage());
        }
        return response;
    }

    @Override
    protected void onPostExecute(NetworkResponse response) {
        if (response != null && response.responseCode == HttpsURLConnection.HTTP_OK)
            mListener.onSuccess(response.responseCode, response.response);
        else
            mListener.onFailure(response.responseCode, response.errorMessage);
    }
}
