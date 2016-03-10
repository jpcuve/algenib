package com.darts.algenib.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "docket", catalog = "darts")
@Entity
public class Docket {
    @ManyToOne
    @JoinColumn(name = "binder_fk")
    private Binder binder;
    @ManyToOne
    @JoinColumn(name = "court_fk")
    private Court court;
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @OneToMany(mappedBy = "docket")
    private List<Decision> decisions;

    public Binder getBinder() {
        return binder;
    }

    public void setBinder(Binder binder) {
        this.binder = binder;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
    }
}
