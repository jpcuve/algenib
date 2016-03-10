package com.darts.algenib.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "court", catalog = "darts")
@Entity
public class Court {
    @ManyToOne
    @JoinColumn(name = "parent_fk")
    private Court parent;
    @ManyToOne
    @JoinColumn(name = "matching_country_fk")
    private Country country;
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "lid")
    private long lid;
    @Basic
    @Column(name = "rid")
    private long rid;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    public String getFullName(){
        final List<String> list = new ArrayList<>();
        for (Court court = this; court != null; court = court.getParent()){
            list.add(0, court.getName());
        }
        return list.stream().collect(Collectors.joining("|"));
    }

    public Court getParent() {
        return parent;
    }

    public void setParent(Court parent) {
        this.parent = parent;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLid() {
        return lid;
    }

    public void setLid(long lid) {
        this.lid = lid;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
