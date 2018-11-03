package challenge.coding.uber.imagesearchapp.webservice;

public class NetworkResponse {

    public int responseCode;
    public String response;
    public String errorMessage;

    public NetworkResponse(){

    }

    public NetworkResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public NetworkResponse(int responseCode, String response){
        this.responseCode = responseCode;
        this.response = response;
    }

    public NetworkResponse(int responseCode){
        this.responseCode = responseCode;
    }

}
