package com.darts.algenib.entity;

import javax.persistence.*;

@Table(name = "analysis", catalog = "darts")
@Entity
public class Analysis {
    @ManyToOne
    @JoinColumn(name = "decision_fk")
    private Decision decision;
    @ManyToOne
    @JoinColumn(name = "pol_fk")
    private Pol pol;
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "pos")
    private int pos;
    @Basic
    @Column(name = "neg")
    private int neg;

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getNeg() {
        return neg;
    }

    public void setNeg(int neg) {
        this.neg = neg;
    }
}
