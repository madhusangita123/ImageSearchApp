package challenge.coding.uber.imagesearchapp.webservice;

public interface NetworkSourceListener {

    void onSuccess(Object response);
    void onFailure(int responseCode,String error);
}
