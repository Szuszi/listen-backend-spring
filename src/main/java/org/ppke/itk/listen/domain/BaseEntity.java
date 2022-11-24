package org.ppke.itk.listen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "last_modified_on")
    private Date modifiedOn;

    @PrePersist
    protected void onCreate() {
        modifiedOn = createdOn = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedOn = new Date();
    }
}

