package com.etip.ezugi.app.implementors;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.etip.ezugi.app.entity.ClientDetailsEntity;
import com.etip.ezugi.app.repository.ClientDetailsRepository;

@Component
public class ClientAuthServicesImpl implements UserDetailsService {

    @Autowired
    ClientDetailsRepository clientDetailsRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ClientDetailsEntity> clientDetailsEntity = Optional.ofNullable(clientDetailsRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));

        return ClientDetailsServiceImpl.buildClient(clientDetailsEntity.get());
    }
}
