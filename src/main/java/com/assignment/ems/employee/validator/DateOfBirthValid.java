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
@Constraint(validatedBy = DateOfBirthValidator.class)
public @interface DateOfBirthValid {
    String message() default "Date of birth is invalid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}