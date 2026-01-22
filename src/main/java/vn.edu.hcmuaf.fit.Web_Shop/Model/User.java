package vn.edu.hcmuaf.fit.Web_Shop.Model;

public class User {
    private int id;
    private String email;
    private String username;
    private String password;
    private String role;
    private int locked;

    public User() {}

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = "USER";
        this.locked = 0;
    }

    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

