package com.egs.shopapp.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class RoleSubSetValidator implements ConstraintValidator<RoleSubSet, String> {
    private String[] subset;

    @Override
    public void initialize(RoleSubSet constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
