package com.github.szsalyi.pulzator.products.validator;

import com.github.szsalyi.pulzator.products.Product;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductForSearchValidator implements ConstraintValidator<ProductValidForSearch, Product> {
@   Override
    public boolean isValid(final Product value, final ConstraintValidatorContext context) {
    String name = value.getName();
    return (name != null && name.length() > 0) || value.getPrice() > 0.0;
    }
}
