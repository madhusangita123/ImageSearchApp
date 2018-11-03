package challenge.coding.uber.imagesearchapp.datarepository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import challenge.coding.uber.imagesearchapp.AppConstants;
import challenge.coding.uber.imagesearchapp.model.Photo;
import challenge.coding.uber.imagesearchapp.model.SearchImageResponse;
import challenge.coding.uber.imagesearchapp.webservice.NetworkSource;
import challenge.coding.uber.imagesearchapp.webservice.NetworkSourceListener;


public class PhotoCollectionSource extends PageKeyedDataSource<Long, Photo> {

    private String mSearchItem;
    private NetworkSource mSource;


    public PhotoCollectionSource(String searchItem) {
        this.mSearchItem = searchItem;
        mSource = new NetworkSource();
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Photo> callback) {


        mSource.searchImages( mSearchItem,1, new NetworkSourceListener() {
            @Override
            public void onSuccess(Object response) {
                SearchImageResponse searchImageResponse = (SearchImageResponse) response;
                if (searchImageResponse.photoCollection != null)
                    callback.onResult( searchImageResponse.photoCollection.photos, null, 2l );
            }

            @Override
            public void onFailure(int responseCode, String error) {
                loadInitial(params,callback);
            }
        } );
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Photo> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Photo> callback) {

        mSource.searchImages( mSearchItem, params.key, new NetworkSourceListener() {
            @Override
            public void onSuccess(Object response) {
                SearchImageResponse searchImageResponse = (SearchImageResponse) response;
                Long nextKey = (params.key == searchImageResponse.photoCollection.numberOfPages) ? null : params.key + 1;
                if (nextKey != null)
                    callback.onResult( searchImageResponse.photoCollection.photos, nextKey );
            }

            @Override
            public void onFailure(int responseCode, String error) {
                loadAfter(params,callback);
            }
        } );


    }
}
