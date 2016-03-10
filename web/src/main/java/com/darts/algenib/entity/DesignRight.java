package com.darts.algenib.entity;

import javax.persistence.*;

@Table(name = "design_right", catalog = "darts")
@DiscriminatorValue("DM")
@Entity
public class DesignRight extends Right {
    @Basic
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
