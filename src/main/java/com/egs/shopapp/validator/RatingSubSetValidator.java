package com.egs.shopapp.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.egs.shopapp.model.enums.Rating;

public class RatingSubSetValidator implements ConstraintValidator<RatingSubSet, Rating> {
    private Rating[] subset;

    @Override
    public void initialize(RatingSubSet constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Rating value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    } 
}
