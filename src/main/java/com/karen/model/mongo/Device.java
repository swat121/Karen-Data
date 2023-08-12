package com.karen.model.mongo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor(onConstructor_ = {@JsonCreator})
public class Device {

    @NonNull
    @JsonProperty("name")
    private final String name;
    private List<com.karen.model.mongo.Data> data;

}
