package challenge.coding.uber.imagesearchapp.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.logging.LogRecord;

import javax.net.ssl.HttpsURLConnection;

public class ImageLoader {

    private static ImageLoader thisInstance;

    private MemoryCache cache;
    private ExecutorService executorService;
    private Handler uiHandler;

    private ImageLoader(){
        cache = new MemoryCache();
        executorService = Executors.newScheduledThreadPool( Runtime.getRuntime().availableProcessors() );
        uiHandler = new Handler(Looper.getMainLooper());

    }

    public static ImageLoader getInstance(){
        if(thisInstance == null){
            thisInstance = new ImageLoader();
        }
        return thisInstance;
    }

    public void loadImage(final String url, final ImageView imageView){
        Bitmap chached = cache.get( url );
        if(chached!=null) {
            updateImageView( imageView, chached );
            return;
        }

        imageView.setTag( url );
        executorService.submit( new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage( url );
                if (bitmap != null) {
                    if (imageView.getTag() == url) {
                        updateImageView(imageView, bitmap);
                    }
                    cache.put(url, bitmap);
                }
            }
        } );
    }

    public void clearCache(){
        cache.clear();
    }

    private void updateImageView(final ImageView imageView, final Bitmap bitmap){
        uiHandler.post( new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap( bitmap );
            }
        } );
    }

    private Bitmap downloadImage(String url){
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        try{
            URL uRL = new URL( url );
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
            connection.disconnect();
        }catch (IOException e){
            e.printStackTrace();
        }

        return bitmap;
    }
}
