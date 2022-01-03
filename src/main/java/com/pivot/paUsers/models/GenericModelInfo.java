package com.pivot.paUsers.models;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public class GenericModelInfo implements Serializable {
    @Column(name = "system_id")
    private UUID systemId = UUID.randomUUID();
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    protected void onCreateAt() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdateAt() {
        updatedAt = new Date();
    }
}
