package challenge.coding.uber.imagesearchapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import challenge.coding.uber.imagesearchapp.datarepository.PhotoCollectionSource;
import challenge.coding.uber.imagesearchapp.datarepository.PhotoCollectionSourceFactory;
import challenge.coding.uber.imagesearchapp.model.Photo;

public class PhotosViewModel extends ViewModel {

    private LiveData<PagedList<Photo>> photosData;
    private String mSearchItem;
    private PhotoCollectionSourceFactory photoDataFactory;
    private PhotoCollectionSource photoCollectionSource;

    public PhotosViewModel(String searchItem){
        this.mSearchItem = searchItem;
        init();
    }

    private void init() {
        Executor executor = Executors.newFixedThreadPool(5);

        photoDataFactory = new PhotoCollectionSourceFactory(mSearchItem);
        photoCollectionSource = (PhotoCollectionSource) photoDataFactory.create();
        photosData = (new LivePagedListBuilder(photoDataFactory,100))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Photo>> getphotosData() {
        return photosData;
    }

    public void invalidateDataSource() {
        photoCollectionSource.invalidate();
    }

}
