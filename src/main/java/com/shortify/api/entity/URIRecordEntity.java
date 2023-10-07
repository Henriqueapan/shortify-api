package com.shortify.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.ejb.Local;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * Example JPA entity defined as a Panache Entity.
 * An ID field of Long type is provided, if you want to define your own ID field extends <code>PanacheEntityBase</code> instead.
 *
 * This uses the active record pattern, you can also use the repository pattern instead:
 * .
 *
 * Usage (more example on the documentation)
 *
 * {@code
 *     public void doSomething() {
 *         MyEntity entity1 = new MyEntity();
 *         entity1.field = "field-1";
 *         entity1.persist();
 *
 *         List<MyEntity> entities = MyEntity.listAll();
 *     }
 * }
 */
@Entity
@Table(
        name = "url"
)
@Getter
@Setter
@NoArgsConstructor
public class URIRecordEntity extends PanacheEntity {
    public String longUri;

    public String shortUriHash;

    public LocalDateTime creationTime;

    public LocalDateTime updateTime;

    public URIRecordEntity(String longUri) {
        this.longUri = longUri;
    }

    public static URIRecordEntity findByHash(String shortUriHash) {
        return find("shortUriHash", shortUriHash).firstResult();
    }

    public static URIRecordEntity findByLongUri(String longUri) {
        return find("longUri", longUri).firstResult();
    }
}
