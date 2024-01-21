package com.karen.configuration;

import com.karen.model.mongo.BoardConfig;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexDefinition;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class MongoConfig {
    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndexes() {
        IndexDefinition indexDefinitionName = new Index("name", Sort.Direction.ASC).named("name_index").unique();
        mongoTemplate.indexOps(BoardConfig.class).ensureIndex(indexDefinitionName);
    }
}
