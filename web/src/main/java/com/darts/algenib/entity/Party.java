package com.darts.algenib.entity;

import javax.persistence.*;

@Table(name = "binder_mbr", catalog = "darts")
@Entity
public class Party implements Sided {
    @ManyToOne
    @JoinColumn(name = "binder_fk")
    private Binder binder;
    @ManyToOne
    @JoinColumn(name = "member_fk")
    private Member member;
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "opponent")
    private boolean opponent;
    @Basic
    @Column(name = "anonymous")
    private boolean anonymous;
    @Basic
    @Column(name = "ex_officio")
    private boolean exOfficio;

    public Binder getBinder() {
        return binder;
    }

    public void setBinder(Binder binder) {
        this.binder = binder;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isOpponent() {
        return opponent;
    }

    public void setOpponent(boolean opponent) {
        this.opponent = opponent;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public boolean isExOfficio() {
        return exOfficio;
    }

    public void setExOfficio(boolean exOfficio) {
        this.exOfficio = exOfficio;
    }
}
