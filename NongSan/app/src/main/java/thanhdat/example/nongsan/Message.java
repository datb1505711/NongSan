package thanhdat.example.nongsan;

public class Message {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String message;
    private String name;

    public Message() {
    }

    public Message(String message, String name) {
        this.message = message;
        this.name = name;
    }
}
