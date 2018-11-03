package challenge.coding.uber.imagesearchapp;

public interface AppConstants {

    String recentPhotosAPI = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&\n" +
            "format=json&nojsoncallback=1&safe_search=1&text=rose&page=%d";

    String searchImageAPI = "https://api.flickr.com/services/rest/?method=flickr.photos.search" +
            "&api_key=3e7cc266ae2b0e0d78e279ce8e361736" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&safe_search=1" +
            "&text=%s" +
            "&page=%d";

    String photoImageURL = "http://farm%s.static.flickr.com/%s/%s_%s.jpg";


    String SEARCH_IMAGE_TOKEN ="SEARCH_IMAGE_TOKEN";
    String GET_INITIAL_PHOTOS = "GET_RECENT_PHOTOS";
    String INITIAL_PHOTO_ITEM = "rose";

    interface JSONKeys{
        String stat = "stat";
        String photos = "photos";
        String page = "page";
        String pages = "pages";
        String perpage = "perpage";
        String total = "total";
        String photo = "photo";
        String id = "id";
        String secret = "secret";
        String server = "server";
        String farm = "farm";
    }
}
