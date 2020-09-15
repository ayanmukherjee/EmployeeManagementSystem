package com.assignment.ems.employee.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Constraint(validatedBy = GenderValidator.class)
public @interface GenderValid {
    String message() default "Gender is invalid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}