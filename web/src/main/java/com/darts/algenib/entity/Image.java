package com.darts.algenib.entity;

import javax.persistence.*;

/**
 * Created by jpc on 2/19/16.
 */
@Table(name = "image", catalog = "darts")
@Entity
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "digest")
    private String digest;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    @Override
    public int hashCode() {
        return digest.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Image) && ((Image) obj).digest.equals(digest);
    }
}
