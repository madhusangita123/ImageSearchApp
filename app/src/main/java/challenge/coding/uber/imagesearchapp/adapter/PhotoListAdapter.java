package challenge.coding.uber.imagesearchapp.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import challenge.coding.uber.imagesearchapp.R;
import challenge.coding.uber.imagesearchapp.model.Photo;

public class PhotoListAdapter extends PagedListAdapter<Photo,RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater = null;

    public PhotoListAdapter(Context context){
        super(Photo.DIFF_CALLBACK);
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.image_item,parent, false);
        PhotoViewHolder viewHolder = new PhotoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PhotoViewHolder viewHolder = (PhotoViewHolder)holder;
        challenge.coding.uber.imagesearchapp.imageloader.ImageLoader loader
                = challenge.coding.uber.imagesearchapp.imageloader.ImageLoader.getInstance();
        loader.loadImage(getItem(position).getUrlToImage(),viewHolder.mPhotoImgView);
        //viewHolder.bindTo(getItem(position));
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder{

        private ImageView mPhotoImgView;

        public PhotoViewHolder(View view){
            super(view);
            mPhotoImgView = view.findViewById( R.id.photo_imgview);
        }

        public void bindTo(Photo photo){
            //Picasso.get().load(photo.getUrlToImage()).into(mPhotoImgView);
            //ImageLoader loader = new ImageLoader(photo.getUrlToImage(),mPhotoImgView);
            //loader.execute();
        }
    }

}
