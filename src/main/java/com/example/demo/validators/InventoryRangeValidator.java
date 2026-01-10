package com.example.demo.validators;

import com.example.demo.domain.Part;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Part H: Validator to ensure inventory is between minimum and maximum values
 */
public class InventoryRangeValidator implements ConstraintValidator<ValidInventoryRange, Part> {
    
    @Override
    public void initialize(ValidInventoryRange constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext constraintValidatorContext) {
        // Part H: Validate inventory is within min/max range
        if (part.getInv() < part.getMinInv()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Inventory is below minimum level of " + part.getMinInv())
                    .addConstraintViolation();
            return false;
        }
        if (part.getInv() > part.getMaxInv()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Inventory exceeds maximum level of " + part.getMaxInv())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
