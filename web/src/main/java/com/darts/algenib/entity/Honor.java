package com.darts.algenib.entity;

import javax.persistence.*;

/**
 * Created by jpc on 2/19/16.
 */
@Table(name = "honor", catalog = "darts")
@Entity
public class Honor {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "decision_fk")
    private Decision decision;
    @ManyToOne
    @JoinColumn(name = "right_fk")
    private Right right;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }
}
