package com.etip.ezugi.app.implementors;

import com.etip.ezugi.app.entity.ClientDetailsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClientDetailsServiceImpl implements UserDetails {

    private final String clientId;

    private String clientSecret;

    private final String userName;

    private final String email;

    private final String mobileNo;

    @JsonIgnore
    private String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public ClientDetailsServiceImpl(String clientId, String clientSecret, String userName, String email,
                                    String mobileNo, String password,
                                    Collection<? extends GrantedAuthority> authorities) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.userName = userName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.password = password;
        this.authorities = authorities;
    }

    public ClientDetailsServiceImpl(String clientId, String userName, String email,
                                    String mobileNo, Collection<? extends GrantedAuthority> authorities) {

        this.clientId = clientId;
        this.userName = userName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.authorities = authorities;

    }

    public static ClientDetailsServiceImpl buildClient(ClientDetailsEntity clientDetailsEntity) {
        List<GrantedAuthority> authorities = clientDetailsEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());


        return new ClientDetailsServiceImpl(clientDetailsEntity.getClientId(),
                clientDetailsEntity.getUserName(), clientDetailsEntity.getEmail(),
                clientDetailsEntity.getMobileNo(), authorities);
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ClientDetailsServiceImpl client = (ClientDetailsServiceImpl) o;
        return Objects.equals(clientId, client.clientId);
    }
}
