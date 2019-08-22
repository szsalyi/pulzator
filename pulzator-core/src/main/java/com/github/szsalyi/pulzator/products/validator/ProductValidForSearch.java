package com.github.szsalyi.pulzator.products.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProductForSearchValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface ProductValidForSearch {
    String message() default "{com.github.szsalyi.pulzator.products.validator.ProductValidForSearch.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
