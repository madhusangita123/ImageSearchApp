package challenge.coding.uber.imagesearchapp.webservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import challenge.coding.uber.imagesearchapp.AppConstants;
import challenge.coding.uber.imagesearchapp.model.Photo;
import challenge.coding.uber.imagesearchapp.model.PhotoCollection;
import challenge.coding.uber.imagesearchapp.model.SearchImageResponse;

public class JsonParser {

    public static SearchImageResponse parseResponse(String response){

        SearchImageResponse searchImageResponse = new SearchImageResponse();

        try {
            JSONObject jsonObject = new JSONObject( response );
            searchImageResponse.stat = jsonObject.getString(AppConstants.JSONKeys.stat);

            JSONObject photosJSON = jsonObject.getJSONObject(AppConstants.JSONKeys.photos);

            if(photosJSON!=null){
                PhotoCollection photoCollection = new PhotoCollection();
                photoCollection.pageNumber = photosJSON.getInt(AppConstants.JSONKeys.page);
                photoCollection.numberOfPages = photosJSON.getInt(AppConstants.JSONKeys.pages);
                photoCollection.perPage = photosJSON.getInt(AppConstants.JSONKeys.perpage);
                photoCollection.total = photosJSON.getInt(AppConstants.JSONKeys.total);

                JSONArray photoArray = photosJSON.getJSONArray(AppConstants.JSONKeys.photo);
                if(photoArray!=null && photoArray.length()>0){
                    List<Photo> photos = new ArrayList<>();
                    for(int i=0; i<photoArray.length(); i++){
                        Object object = photoArray.get(i);
                        if(object instanceof JSONObject){
                            JSONObject photoJSON = (JSONObject)object;
                            Photo photo = new Photo();
                            photo.id = photoJSON.getString(AppConstants.JSONKeys.id);
                            photo.server = photoJSON.getString(AppConstants.JSONKeys.server);
                            photo.secret = photoJSON.getString(AppConstants.JSONKeys.secret);
                            photo.farm = photoJSON.getString(AppConstants.JSONKeys.farm);
                            photos.add(photo);
                        }
                    }
                    photoCollection.photos = photos;
                }

                searchImageResponse.photoCollection = photoCollection;
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return searchImageResponse;

    }
}
