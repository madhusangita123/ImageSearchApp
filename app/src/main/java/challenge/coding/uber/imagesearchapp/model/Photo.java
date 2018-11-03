package challenge.coding.uber.imagesearchapp.model;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import challenge.coding.uber.imagesearchapp.AppConstants;

public class Photo {
    //{"id":"44855780794","owner":"38695222@N00",
    // "secret":"b7b4290780","server":"1955","farm":2,
    // "title":"20170910_091333","ispublic":1,"isfriend":0,"isfamily":0

    //http://farm{farm}.static.flickr.com/{server}/{id}_{secret}.jpg
    public String id;
    public String secret;
    public String server;
    public String farm;

    public String getUrlToImage(){
        return String.format( AppConstants.photoImageURL,farm,server,id,secret);
    }

    public static DiffUtil.ItemCallback<Photo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.id.equalsIgnoreCase(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        Photo photoObj = (Photo)obj;
        if(photoObj.id.equals(this.id)
                && photoObj.secret.equals(this.secret)
                && photoObj.server.equals( this.server)
                && photoObj.farm.equals( this.farm )){
            return true;
        }

        return false;
    }
}
