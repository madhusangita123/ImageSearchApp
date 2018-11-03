package challenge.coding.uber.imagesearchapp.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import challenge.coding.uber.imagesearchapp.AppConstants;
import challenge.coding.uber.imagesearchapp.R;
import challenge.coding.uber.imagesearchapp.adapter.PhotoListAdapter;
import challenge.coding.uber.imagesearchapp.imageloader.ImageLoader;
import challenge.coding.uber.imagesearchapp.model.Photo;
import challenge.coding.uber.imagesearchapp.utils.AppUtils;
import challenge.coding.uber.imagesearchapp.utils.NetworkChangeReceiver;
import challenge.coding.uber.imagesearchapp.viewmodel.PhotosViewModel;

public class MainActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkChangeListener{

    private String mSearchString = null;
    private PhotoListAdapter mPhotosAdapter;
    private RecyclerView mPhotoListView;
    private SearchView mImageSearchBar;
    private Snackbar mSnackbar;
    private NetworkChangeReceiver mreciever;
    private PhotosViewModel mPhotosViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhotoListView = findViewById(R.id.photos_lv);
        mPhotoListView.setLayoutManager(new GridLayoutManager( this,3 ));
        mPhotoListView.setItemAnimator(new DefaultItemAnimator());

        mPhotosAdapter = new PhotoListAdapter( this);
        mPhotoListView.setAdapter(mPhotosAdapter);

        mImageSearchBar = findViewById( R.id.image_search_bar );
        mImageSearchBar.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                if(mSearchString == null) {
                    mSearchString = s;
                    loadSearchResult(mSearchString);
                }else if(!mSearchString.equals(s)){
                    mSearchString = s;
                    loadSearchResult(mSearchString);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        } );

        mreciever = new NetworkChangeReceiver(this);

        loadInitialPhotos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction( ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mreciever,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mreciever);
    }

    private void loadInitialPhotos(){
        mImageSearchBar.setQuery(AppConstants.INITIAL_PHOTO_ITEM,true  );
        mImageSearchBar.setIconified( false );
        mImageSearchBar.clearFocus();
      //loadSearchResult(AppConstants.INITIAL_PHOTO_ITEM);
    }

    private void loadSearchResult(String searchText){
        if(AppUtils.isConnected( this )){
            ImageLoader.getInstance().clearCache();
           mPhotosViewModel = new PhotosViewModel(searchText);
            LiveData<PagedList<Photo>> photosLivedat = mPhotosViewModel.getphotosData();
            photosLivedat.observe( this, new Observer<PagedList<Photo>>() {
                @Override
                public void onChanged(@Nullable PagedList<Photo> photos) {
                    mPhotosAdapter.submitList( photos );
                }
            } );
        }else{
            showErrorMessage( getString(R.string.no_connection));
        }
    }

    private void showErrorMessage(String message){
        CoordinatorLayout coordinatorLayout = findViewById( R.id.main_layout);
        mSnackbar = Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_INDEFINITE);
        mSnackbar.show();
    }


    @Override
    public void onNetworkChange(boolean isConnected) {
        if(!isConnected)
            showErrorMessage( getString(R.string.no_connection));
        else{
            if(mSnackbar!=null && mSnackbar.isShown())
                mSnackbar.dismiss();

            //mPhotosViewModel.invalidateDataSource();
            if(mPhotosViewModel == null)
                loadSearchResult( mSearchString==null?AppConstants.INITIAL_PHOTO_ITEM:mSearchString );
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoader.getInstance().clearCache();
    }
}
