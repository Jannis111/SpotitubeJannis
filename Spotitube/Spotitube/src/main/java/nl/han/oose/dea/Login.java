package nl.han.oose.dea;

public class Login {


    public Login() {
    }

    public Login(int token, String user, String password) {
        this.token = token;
        this.user = user;
        this.password = password;
    }

    private int token;
    private String user;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
