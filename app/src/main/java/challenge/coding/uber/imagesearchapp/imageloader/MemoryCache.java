package challenge.coding.uber.imagesearchapp.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;


public class MemoryCache implements ImageCache {

    LruCache<String,Bitmap> cache;


    public MemoryCache(){
        long maxmemory = Runtime.getRuntime().maxMemory()/1024;
        int cacheSize = (int)(maxmemory/4);
        cache = new LruCache<String,Bitmap>(100){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return super.sizeOf( key, value );
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        cache.put( url,bitmap );
    }

    @Override
    public Bitmap get(String url) {
        return cache.get( url );
    }

    @Override
    public void clear() {
        cache.evictAll();
    }
}
