package com.darts.algenib.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "product_comparison", catalog = "darts")
@Entity
public class ProductComparison {
    @ManyToOne
    @JoinColumn(name = "decision_fk")
    private Decision decision;
    @OneToMany(mappedBy = "productComparison")
    @OrderBy("id")
    private List<ProductComparisonSide> productComparisonSides;
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "similar")
    private boolean similar;
    @Basic
    @Column(name = "similar", insertable = false, updatable = false)
    private int similarityCount;

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public List<ProductComparisonSide> getProductComparisonSides() {
        return productComparisonSides;
    }

    public void setProductComparisonSides(List<ProductComparisonSide> productComparisonSides) {
        this.productComparisonSides = productComparisonSides;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSimilar() {
        return similar;
    }

    public void setSimilar(boolean similar) {
        this.similar = similar;
    }

    public int getSimilarityCount() {
        return similarityCount;
    }

    public void setSimilarityCount(int similarityCount) {
        this.similarityCount = similarityCount;
    }
}
