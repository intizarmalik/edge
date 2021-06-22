package com.etip.ezugi.app.repository;

public interface ClientDetailsRepositoryCustom {
    boolean isClientExistsByClientIdAndClientSecret(String clientId, String clientSecret);
}
