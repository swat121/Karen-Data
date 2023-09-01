package com.karen.model.mongo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document
@RequiredArgsConstructor(onConstructor_ = {@JsonCreator})
public class BoardConfig {
    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull
    @JsonProperty("name")
    private String name;

    private Setting setting;
}
