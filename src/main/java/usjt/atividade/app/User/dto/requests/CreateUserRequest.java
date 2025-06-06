package usjt.atividade.app.User.dto.requests;

public class CreateUserRequest {

    private String fullname;
    private String email;
    private String password;

    public CreateUserRequest(String fullname, String email, String password){
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
