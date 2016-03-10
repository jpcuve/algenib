package com.darts.algenib.entity;

import javax.persistence.*;

@Table(name = "invention", catalog = "darts")
@Entity
public class Invention {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "src_application_id", nullable = true)
    private Long srcApplicationId;
    @Basic
    @Column(name = "questel_xap", nullable = true)
    private String questelXap;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getSrcApplicationId() {
        return srcApplicationId;
    }

    public void setSrcApplicationId(Long srcApplicationId) {
        this.srcApplicationId = srcApplicationId;
    }

    public String getQuestelXap() {
        return questelXap;
    }

    public void setQuestelXap(String questelXap) {
        this.questelXap = questelXap;
    }
}
