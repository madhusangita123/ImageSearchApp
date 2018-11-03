package challenge.coding.uber.imagesearchapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkChangeReceiver extends BroadcastReceiver {

    NetworkChangeListener listener;


    public NetworkChangeReceiver(NetworkChangeListener listener) {
         this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        listener.onNetworkChange(AppUtils.isConnected(context));

    }

    public interface NetworkChangeListener{
        void onNetworkChange(boolean isConnected);
    }
}
