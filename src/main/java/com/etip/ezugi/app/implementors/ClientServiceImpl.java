package com.etip.ezugi.app.implementors;

import com.etip.ezugi.app.repository.ClientDetailsRepositoryCustom;
import com.etip.ezugi.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;


public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientDetailsRepositoryCustom clientDetailsRepositoryCustom;

    @Override
    public boolean clientExistsByClientIdAndClientSecret(String clientId, String clientSecret) {
        return clientDetailsRepositoryCustom.isClientExistsByClientIdAndClientSecret(clientId, clientSecret);

    }
}
