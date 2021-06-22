package com.etip.ezugi.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Document(collection = "clientDetails")
public class ClientDetailsEntity {

    @Id
    private String clientId;
    private String clientSecret;
    @Size(min = 8, max = 25)
    private String password;
    @Size(min = 5, max = 10)
    private String userName;
    @Size(min = 7, max = 40)
    private String email;
    @Size(min = 10, max = 10)
    private String mobileNo;
    @NotBlank
    private Set<ClientsRolesEntity> roles;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Set<ClientsRolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<ClientsRolesEntity> roles) {
        this.roles = roles;
    }
}
