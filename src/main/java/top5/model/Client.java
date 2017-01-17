package top5.model;

public class Client {

    private String clientKey;
    private String clientName;

    public Client (String clientKey, String clientName){
        this.clientKey = clientKey;
        this.clientName = clientName;
        
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
