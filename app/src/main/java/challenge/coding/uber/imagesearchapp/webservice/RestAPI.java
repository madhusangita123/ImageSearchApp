package challenge.coding.uber.imagesearchapp.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public  class RestAPI {

    /**
     * Given a URL, sets up a connection and gets the HTTP response body from the server.
     * If the network request is successful, it returns the response body in String form. Otherwise,
     * it will throw an IOException.
     */
    public NetworkResponse getPhotoCollection(URL url) throws IOException {
        NetworkResponse networkResponse = null;
        String response = null;
        HttpURLConnection connection = null;
        InputStream stream = null;
        try {
            connection = (HttpsURLConnection)(url).openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                networkResponse = new NetworkResponse(responseCode);
            }else{
                stream = connection.getInputStream();
                if (stream != null) {
                    response = getInputStream(stream);
                }
                networkResponse = new NetworkResponse(responseCode,response);
            }

        }finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return networkResponse;
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    public static String getInputStream(InputStream inputStream)throws IOException{
        String result = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        result = sb.toString();

        return result;
    }

}
