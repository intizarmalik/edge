package com.etip.ezugi.app.repository;

import com.etip.ezugi.app.entity.ClientDetailsEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Primary
public interface ClientDetailsRepository extends MongoRepository<ClientDetailsEntity, Object>, ClientDetailsRepositoryCustom {

    Optional<ClientDetailsEntity> findByUserName(@NotNull String username);

    boolean existsById(@NotNull String merchant_id);

    boolean existsByUserName(@NotNull String username);

    boolean existsByEmail(@NotNull String email);

    boolean existsByMobileNo(@NotNull String mobileNo);
}
