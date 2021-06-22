package com.etip.ezugi.app.repository;

import com.etip.ezugi.app.entity.ClientDetailsEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ClientDetailsRepositoryCustomImpl implements ClientDetailsRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    public ClientDetailsRepositoryCustomImpl(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean isClientExistsByClientIdAndClientSecret(String clientId, String clientSecret) {
        Query query = new Query();
        query.addCriteria(Criteria.where("clientId").is(clientId).and("clientSecret").is(clientSecret));
        return mongoTemplate.exists(query, ClientDetailsEntity.class);

    }
}
