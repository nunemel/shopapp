package com.egs.shopapp.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.egs.shopapp.model.enums.Status;

public class StatusSubSetValidator implements ConstraintValidator<StatusSubSet, Status> {
    private Status[] subset;

    @Override
    public void initialize(StatusSubSet constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Status value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
