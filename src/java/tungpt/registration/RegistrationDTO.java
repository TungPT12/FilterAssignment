/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.registration;

/**
 *
 * @author TUNGPT
 */
public class RegistrationDTO {
    private String username;
    private String password;
    private boolean role;
    private String fullname;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, boolean role, String fullname) {
        this.username = username;
        this.role = role;
        this.fullname = fullname;
    }

    public RegistrationDTO(String username, String password, boolean role, String fullname) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
   
}
