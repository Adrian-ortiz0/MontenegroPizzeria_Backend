package org.example.montenegropizzeria.user.domain.DTO;

public class LoginAdminDTO {

    private String email;
    private String password;

    public LoginAdminDTO() {
    }

    public LoginAdminDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
