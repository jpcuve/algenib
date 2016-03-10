package com.darts.algenib.entity;

import javax.persistence.*;

/**
 * Created by jpc on 9/23/15.
 */
@Table(name = "nice_description", catalog = "darts")
@Entity
public class NiceDescription {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "lang")
    private String language;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "nice_fk")
    private Nice nice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Nice getNice() {
        return nice;
    }

    public void setNice(Nice nice) {
        this.nice = nice;
    }
}
