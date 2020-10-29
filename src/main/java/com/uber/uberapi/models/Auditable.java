package com.uber.uberapi.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass // don't create table for Auditable
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)  // Jpa
    @CreatedDate // hibernate
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate // hibernate
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auditable auditable = (Auditable) o;
        if (id == null || auditable.id == null) return false;
        return id.equals(auditable.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}


// compare objects in Java
// ==
// primitive data type - int, bool, float, char - value comparison
// objects - compare the memory addresses

// .equals() - Object.equals()   ==
// .hashCode() - memory address


// hooks


//    @PrePersist   // creation time - id is assigned
//    @PreUpdate    // whenever a change is pushed to the DB
//    void updateTimestamp() {
//
//    }


// VARCHAR(size)
// TEXT
// JSON
// BLOB
// INT
//
