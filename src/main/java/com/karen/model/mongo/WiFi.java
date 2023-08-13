package com.karen.model.mongo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(onConstructor_ = {@JsonCreator})
public class WiFi {
    @NonNull
    @JsonProperty("ip")
    private String ip;

    @NonNull
    @JsonProperty("mac")
    private String mac;

    @NonNull
    @JsonProperty("ssid")
    private String ssid;
}
