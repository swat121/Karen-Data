package com.karen.model.mongo;

import lombok.Data;

import java.util.List;

@Data
public class Device {
    private String name;
    private List<com.karen.model.mongo.Data> data;

}
