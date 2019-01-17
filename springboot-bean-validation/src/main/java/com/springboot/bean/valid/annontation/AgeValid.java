package com.springboot.bean.valid.annontation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {AgeValidConstraint.class})
public @interface AgeValid {

    String message() default "{javax.validation.constraints.Age.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
