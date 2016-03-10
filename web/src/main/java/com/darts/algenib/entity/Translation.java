package com.darts.algenib.entity;

import javax.persistence.*;

/**
 * Created by jpc on 18/12/2015.
 */
@Table(name = "app_translation_entry", catalog = "darts")
@Entity
public class Translation {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "lang", nullable = true)
    private String lang;
    @Basic
    @Column(name = "bundle", nullable = true)
    private String bundle;
    @Basic
    @Column(name = "key_entry", nullable = true)
    private String key;
    @Basic
    @Column(name = "value_entry", nullable = true)
    private String val;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
