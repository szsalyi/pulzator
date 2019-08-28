package com.github.szsalyi.pulzator.products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.szsalyi.pulzator.categories.Category;
import com.github.szsalyi.pulzator.common.audit.DateAudit;
import com.github.szsalyi.pulzator.productmeasures.ProductMeasure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "category")
public class Product extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    @Size(min = 2, max = 31)
    @NotNull
    private String name;

    @Column(name = "QUANTITY")
    @NotNull
    private int quantity;

    @Column(name = "PRICE")
    @NotNull
    private int price;

    @Column(name = "ENABLED")
    @NotNull
    private boolean enabled;

    @ManyToOne
    @NotNull
    private ProductMeasure productMeasure;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id")
    @NotNull
    @JsonBackReference
    private Category category;

}
