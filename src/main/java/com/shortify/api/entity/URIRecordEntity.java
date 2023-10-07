package com.shortify.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;

@Entity
@Table(name = "url")
@Getter
@Setter
@NoArgsConstructor
public class URIRecordEntity extends PanacheEntity {
    @JsonIgnore
    private final ObjectMapper mapper = new ObjectMapper();

    private String longUri;

    private String shortUriHash;

    private LocalDateTime creationTime;

    private LocalDateTime lastUpdateTime;

    public URIRecordEntity(String longUri) {
        this.longUri = longUri;
    }

    public static URIRecordEntity findByHash(String shortUriHash) {
        return find("shortUriHash", shortUriHash).firstResult();
    }

    public static URIRecordEntity findByLongUri(String longUri) {
        return find("longUri", longUri).firstResult();
    }

    public HashMap<String, ?> toHashMap(){
        mapper.findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.convertValue(this, new TypeReference<HashMap<String, ?>>() {});
    }
}
