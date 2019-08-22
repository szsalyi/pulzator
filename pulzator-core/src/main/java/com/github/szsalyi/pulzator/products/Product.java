package com.github.szsalyi.pulzator.products;

import com.github.szsalyi.pulzator.common.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "QUANTITY")
    @NotNull
    private int quantity;

    @ManyToOne
    private ProductMeasure productMeasure;

    @Column(name = "PRICE")
    @NotNull
    private int price;

    @Column(name = "ENABLED")
    @NotNull
    private boolean enabled;
}
