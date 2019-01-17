package com.springboot.bean.valid.annontation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidConstraint implements ConstraintValidator<AgeValid, Integer> {

    @Override
    public void initialize(AgeValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        if (age > 0 && age < 100) {
            return true;
        }
        return false;
    }

}
