package com.darts.algenib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "company_denomination", catalog = "darts")
@Entity
public class CompanyDenomination {
    @Id
    @Column(name = "denomination", nullable = false)
    private String denomination;

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
}
