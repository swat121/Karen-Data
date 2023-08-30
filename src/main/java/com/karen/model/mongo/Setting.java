package com.karen.model.mongo;

import lombok.Data;

import java.util.List;

@Data
public class Setting {
    private List<Device> sensors;
    private List<Device> trackers;
    private List<Device> switchers;
}
