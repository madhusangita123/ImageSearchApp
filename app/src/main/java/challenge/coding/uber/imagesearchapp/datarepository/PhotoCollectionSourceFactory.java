package challenge.coding.uber.imagesearchapp.datarepository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import challenge.coding.uber.imagesearchapp.model.Photo;

public class PhotoCollectionSourceFactory extends DataSource.Factory<Long,Photo> {

    private MutableLiveData<PhotoCollectionSource> mMutablePhotoData;
    private PhotoCollectionSource mPhotoSource;
    private String mSearchItem;


    public PhotoCollectionSourceFactory(String searchItem){
        this.mSearchItem = searchItem;
        this.mMutablePhotoData = new MutableLiveData<>();
    }


    @Override
    public DataSource<Long, Photo> create() {
        mPhotoSource = new PhotoCollectionSource(mSearchItem);
        mMutablePhotoData.postValue(mPhotoSource);
        return mPhotoSource;
    }

    public MutableLiveData<PhotoCollectionSource> getPhotoSource() {
        return mMutablePhotoData;
    }
}
