package org.ecommerce.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String value;

    public GenericEntity() {
    }

    public String getValue() {
        return value;
    }

    public Long getId() {

        return id;
    }

    public GenericEntity(String value) {

        this.value = value;
    }
}
