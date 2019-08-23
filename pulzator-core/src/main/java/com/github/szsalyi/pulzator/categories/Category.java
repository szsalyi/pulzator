package com.github.szsalyi.pulzator.categories;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.szsalyi.pulzator.common.audit.DateAudit;
import com.github.szsalyi.pulzator.products.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 31)
    @NotNull
    private String name;


    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Product> products;
}
