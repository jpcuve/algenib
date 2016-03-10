package com.darts.algenib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jpc on 2/19/16.
 */
@Table(name = "locarno", catalog = "darts")
@Entity
public class Locarno {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Locarno) && ((Locarno)obj).id.equals(id);
    }
}
