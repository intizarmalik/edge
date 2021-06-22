package com.etip.ezugi.app.repository;

import com.etip.ezugi.app.entity.ClientsRolesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientsRolesRepository extends MongoRepository<ClientsRolesEntity, Object> {
}
