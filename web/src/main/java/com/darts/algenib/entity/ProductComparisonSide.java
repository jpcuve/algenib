package com.darts.algenib.entity;

import javax.persistence.*;

@Table(name = "product_comparison_side", catalog = "darts")
@Entity
public class ProductComparisonSide {
    @ManyToOne
    @JoinColumn(name = "product_comparison_fk")
    private ProductComparison productComparison;
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "side")
    private boolean side;
    @Basic
    @Column(name = "nice_fk")
    private String nice;

    public ProductComparison getProductComparison() {
        return productComparison;
    }

    public void setProductComparison(ProductComparison productComparison) {
        this.productComparison = productComparison;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSide() {
        return side;
    }

    public void setSide(boolean side) {
        this.side = side;
    }

    public String getNice() {
        return nice;
    }

    public void setNice(String nice) {
        this.nice = nice;
    }
}
