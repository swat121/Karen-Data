package com.karen.model.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Data
@Document
public class BoardConfig {
    @Id
    private String id;
    private String name;
    private Setting setting;
}
