package mail.dto;

public class EmailRequest {

    private String email;

    private String type;

    public EmailRequest(String email, String type) {
        this.email = email;
        this.type = type;
    }

    public EmailRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
