package com.darts.algenib.entity;

import javax.persistence.*;

@Table(name = "pol", catalog = "darts")
@Entity
public class Pol {
    @ManyToOne
    @JoinColumn(name = "parent_fk")
    private Pol parent;
    @ManyToOne
    @JoinColumn(name = "pole_fk")
    private Pole pole;
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
    @Basic
    @Column(name = "pol_area")
    private String area;
    @Basic
    @Column(name = "overall_similarity")
    private boolean overallSimilarity;

    public Pol getParent() {
        return parent;
    }

    public void setParent(Pol parent) {
        this.parent = parent;
    }

    public Pole getPole() {
        return pole;
    }

    public void setPole(Pole pole) {
        this.pole = pole;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isOverallSimilarity() {
        return overallSimilarity;
    }

    public void setOverallSimilarity(boolean overallSimilarity) {
        this.overallSimilarity = overallSimilarity;
    }


}
