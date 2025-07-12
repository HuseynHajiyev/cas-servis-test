package com.cas.casdemo.casservis.service.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Arrays;

public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOneNotNull, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        Field[] fields = value.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .anyMatch(field -> {
                    try {
                        field.setAccessible(true);
                        Object fieldValue = field.get(value);
                        return fieldValue != null && !String.valueOf(fieldValue).trim().isEmpty();
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                });
    }
}