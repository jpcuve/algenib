package com.darts.algenib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jpc on 2/19/16.
 */
@Table(name = "pole", catalog = "darts")
@Entity
public class Pole {
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
