package com.darts.algenib.entity;

import javax.persistence.*;

@Table(name = "trademark_right", catalog = "darts")
@DiscriminatorValue("TM")
@Entity
public class TrademarkRight extends Right {
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "trademark_type")
    private String trademarkType;
    @Basic
    @Column(name = "type_description")
    private String typeDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrademarkType() {
        return trademarkType;
    }

    public void setTrademarkType(String trademarkType) {
        this.trademarkType = trademarkType;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }
}
