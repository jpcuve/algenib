package com.darts.algenib.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "decision", catalog = "darts")
@Entity
public class Decision {
    @ManyToOne
    @JoinColumn(name = "docket_fk")
    private Docket docket;
    @ManyToOne
    @JoinColumn(name = "analyst_fk")
    private User user;
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "judgment_date")
    private Date judgmentDate;
    @OneToMany(mappedBy = "decision")
    private List<Analysis> analyses;
    @OneToMany(mappedBy = "decision")
    private List<Honor> honors;

    public Docket getDocket() {
        return docket;
    }

    public void setDocket(Docket docket) {
        this.docket = docket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getJudgmentDate() {
        return judgmentDate;
    }

    public void setJudgmentDate(Date judgmentDate) {
        this.judgmentDate = judgmentDate;
    }

    public List<Analysis> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analysis> analyses) {
        this.analyses = analyses;
    }

    public List<Honor> getHonors() {
        return honors;
    }

    public void setHonors(List<Honor> honors) {
        this.honors = honors;
    }
}
