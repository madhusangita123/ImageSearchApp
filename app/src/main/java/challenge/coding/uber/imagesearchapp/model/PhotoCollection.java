package challenge.coding.uber.imagesearchapp.model;

import java.util.ArrayList;
import java.util.List;

public class PhotoCollection {

    //{"page":1,"pages":1603,"perpage":100,"total":"160287"
    public int pageNumber;
    public int numberOfPages;
    public int perPage;
    public long total;
    public List<Photo> photos = new ArrayList<>();
}
