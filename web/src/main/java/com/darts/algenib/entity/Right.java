package com.darts.algenib.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jpc on 9/15/15.
 */
@Table(name = "ip_right", catalog = "darts")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "disc", length = 2, discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
        @NamedQuery(name = Right.RIGHT_FIND_BY_BINDER_FETCH_ALL, query = "select r from Right r left join fetch r.niceClasses left join fetch r.viennaClasses left join fetch r.locarnoClasses left join fetch r.images left join fetch r.honors where r.binder.id = :binderId")
})
@Entity
public class Right implements Sided {
    public static final String RIGHT_FIND_BY_BINDER_FETCH_ALL = "right.findByBinderFetchAll";
    @ManyToOne
    @JoinColumn(name = "binder_fk")
    private Binder binder;
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "disc", insertable = false, updatable = false)
    private String disc;
    @Basic
    @Column(name = "opponent")
    private boolean opponent;
    @Basic
    @Column(name = "anonymous")
    private boolean anonymous;
    @ManyToMany
    @JoinTable(name = "right_nice", joinColumns = @JoinColumn(name = "right_fk", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "nice_fk", referencedColumnName = "id"))
    private Set<Nice> niceClasses;
    @ManyToMany
    @JoinTable(name = "right_vienna", joinColumns = @JoinColumn(name = "right_fk", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "vienna_fk", referencedColumnName = "id"))
    private Set<Nice> viennaClasses;
    @ManyToMany
    @JoinTable(name = "right_locarno", joinColumns = @JoinColumn(name = "right_fk", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "locarno_fk", referencedColumnName = "id"))
    private Set<Locarno> locarnoClasses;
    @ManyToMany
    @JoinTable(name = "right_image", joinColumns = @JoinColumn(name = "right_fk", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "image_fk", referencedColumnName = "id"))
    private Set<Image> images;
    @OneToMany(mappedBy = "right")
    private Set<Honor> honors;
    @OneToOne
    @JoinColumn(name = "trademark_id", insertable = false, updatable = false)
    private TrademarkRight trademark;

    public Binder getBinder() {
        return binder;
    }

    public void setBinder(Binder binder) {
        this.binder = binder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
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

    public Set<Nice> getNiceClasses() {
        return niceClasses;
    }

    public void setNiceClasses(Set<Nice> niceClasses) {
        this.niceClasses = niceClasses;
    }

    public Set<Nice> getViennaClasses() {
        return viennaClasses;
    }

    public void setViennaClasses(Set<Nice> viennaClasses) {
        this.viennaClasses = viennaClasses;
    }

    public Set<Locarno> getLocarnoClasses() {
        return locarnoClasses;
    }

    public void setLocarnoClasses(Set<Locarno> locarnoClasses) {
        this.locarnoClasses = locarnoClasses;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Honor> getHonors() {
        return honors;
    }

    public void setHonors(Set<Honor> honors) {
        this.honors = honors;
    }
}
