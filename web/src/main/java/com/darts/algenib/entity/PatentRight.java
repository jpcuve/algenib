package com.darts.algenib.entity;

import javax.persistence.*;

@Table(name = "patent_right", catalog = "darts")
@DiscriminatorValue("PT")
@Entity
public class PatentRight extends Right {
    @ManyToOne
    @JoinColumn(name = "invention_fk")
    private Invention invention;
    @Basic
    @Column(name = "publication")
    private String publication;

    public Invention getInvention() {
        return invention;
    }

    public void setInvention(Invention invention) {
        this.invention = invention;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }
}
