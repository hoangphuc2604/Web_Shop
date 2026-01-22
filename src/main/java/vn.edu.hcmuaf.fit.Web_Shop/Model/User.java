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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getLocked() { return locked; }
    public void setLocked(int locked) { this.locked = locked; }
}
