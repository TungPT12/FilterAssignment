/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tungpt.registration;

import java.io.Serializable;

/**
 *
 * @author TUNGPT
 */
public class RegistrationErrorDTO implements Serializable{
    private String usernameLengErr;
    private String passwordLengErr;
    private String fullnameLengErr;
    private String confirmNotMatch;
    private String usernameDupplicate;

    public RegistrationErrorDTO() {
    }

    public RegistrationErrorDTO(String usernameLengErr, String passwordLengErr, String fullnameLengErr, String confirmNotMatch, String usernameDupplicate) {
        this.usernameLengErr = usernameLengErr;
        this.passwordLengErr = passwordLengErr;
        this.fullnameLengErr = fullnameLengErr;
        this.confirmNotMatch = confirmNotMatch;
        this.usernameDupplicate = usernameDupplicate;
    }

    public String getUsernameLengErr() {
        return usernameLengErr;
    }

    public void setUsernameLengErr(String usernameLengErr) {
        this.usernameLengErr = usernameLengErr;
    }

    public String getPasswordLengErr() {
        return passwordLengErr;
    }

    public void setPasswordLengErr(String passwordLengErr) {
        this.passwordLengErr = passwordLengErr;
    }

    public String getFullnameLengErr() {
        return fullnameLengErr;
    }

    public void setFullnameLengErr(String fullnameLengErr) {
        this.fullnameLengErr = fullnameLengErr;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getUsernameDupplicate() {
        return usernameDupplicate;
    }

    public void setUsernameDupplicate(String usernameDupplicate) {
        this.usernameDupplicate = usernameDupplicate;
    }
    
}
