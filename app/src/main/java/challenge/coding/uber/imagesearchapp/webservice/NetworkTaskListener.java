package challenge.coding.uber.imagesearchapp.webservice;

public interface NetworkTaskListener {

    void onSuccess(int responseCode, String response);
    void onFailure(int responseCode, String statusMessage);
}
