package challenge.coding.uber.imagesearchapp.webservice;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ImageLoader extends AsyncTask<Void,Void,Bitmap>{

    private String url;
    private ImageView imageView;

    public ImageLoader(String url, ImageView imageView){
       this.url = url;
       this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {

        HttpURLConnection connection = null;
        Bitmap bitmap = null;
        try {
            URL uRL = new URL(url);
            connection = (HttpURLConnection)(uRL).openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream( stream );
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute( bitmap );
        if(bitmap!=null)
            imageView.setImageBitmap(bitmap);
    }
}
