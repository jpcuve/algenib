package com.darts.algenib.entity;

import javax.persistence.*;

/**
 * Created by jpc on 2/19/16.
 */
@Table(name = "usr", catalog = "darts")
@Entity
public class User {
    @ManyToOne
    @JoinColumn(name = "account_fk")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "image_fk")
    private Image image;
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
