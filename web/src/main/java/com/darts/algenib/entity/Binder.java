package com.darts.algenib.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Table(name = "binder", catalog = "darts")
@NamedQueries({
        @NamedQuery(name = Binder.BINDER_IDS_FOR_TWO_DEFENDANT_TRADEMARKS, query = "select b.id from Binder b where b.domainTrademark = true and size(b.rights) = 2"),
        @NamedQuery(name = Binder.BINDER_FIND_ONE_FETCH_RIGHTS, query = "select b from Binder b left join fetch b.rights where b.id = :binderId")
})
@Entity
public class Binder {
    public static final List<String> AREAS = Arrays.asList("EUR", "USA", "CWH", "CHN", "BRZ", "JPN", "CIS", "SEA", "LAT", "SKR", "AFR");
    public static final String BINDER_IDS_FOR_TWO_DEFENDANT_TRADEMARKS = "binder.idsForTwoDefendantTrademarks";
    public static final String BINDER_FIND_ONE_FETCH_RIGHTS = "binder.findOneFetchRights";
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "area")
    private String area;
    @Basic
    @Column(name = "domain_trademark")
    private boolean domainTrademark;
    @Basic
    @Column(name = "domain_patent")
    private boolean domainPatent;
    @Basic
    @Column(name = "first_action")
    private String firstAction;
    @OneToMany(mappedBy = "binder")
    private List<Right> rights;
    @OneToMany(mappedBy = "binder")
    private List<Party> parties;
    @OneToMany(mappedBy = "binder")
    private List<Docket> dockets;

    public int countRights(boolean opponent, Class<? extends Right> rightClass){
        int count = 0;
        if (rights != null){
            for (Right right: rights){
                if (right.isOpponent() == opponent && rightClass.isAssignableFrom(right.getClass())){
                    count++;
                }
            }
        }
        return count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isDomainTrademark() {
        return domainTrademark;
    }

    public void setDomainTrademark(boolean domainTrademark) {
        this.domainTrademark = domainTrademark;
    }

    public boolean isDomainPatent() {
        return domainPatent;
    }

    public void setDomainPatent(boolean domainPatent) {
        this.domainPatent = domainPatent;
    }

    public String getFirstAction() {
        return firstAction;
    }

    public void setFirstAction(String firstAction) {
        this.firstAction = firstAction;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    public List<Docket> getDockets() {
        return dockets;
    }

    public void setDockets(List<Docket> dockets) {
        this.dockets = dockets;
    }
}
