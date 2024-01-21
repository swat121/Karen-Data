package com.karen.model.postgres;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@AllArgsConstructor(onConstructor_ = {@JsonCreator})
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @JsonProperty("ip")
    private String ip;

    @NonNull
    @JsonProperty("name")
    private String name;

    @NonNull
    @JsonProperty("mac")
    private String mac;

    @NonNull
    @JsonProperty("ssid")
    private String ssid;

    @NonNull
    @JsonProperty("version")
    private String version;
}
