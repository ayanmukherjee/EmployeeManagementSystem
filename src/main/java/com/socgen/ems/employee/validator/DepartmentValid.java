package com.socgen.ems.employee.validator;

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
@Constraint(validatedBy = DepartmentValidator.class)
public @interface DepartmentValid {
    String message() default "Department is invalid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}