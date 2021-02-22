package com.egs.shopapp.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.egs.shopapp.model.enums.Condition;


public class ConditionSubSetValidator implements ConstraintValidator<ConditionSubSet, Condition> {
    private Condition[] subset;

    @Override
    public void initialize(ConditionSubSet constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Condition value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
