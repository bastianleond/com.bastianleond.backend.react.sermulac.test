package com.bastianleond.testprevired.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class RutValidator implements ConstraintValidator<ValidateRut, String> {

    private final Pattern rutPattern = Pattern.compile("^\\d{7,8}-[0-9kK]$");

    @Override
    public boolean isValid(String rutToValidate, ConstraintValidatorContext constraintValidatorContext) {

        if (rutToValidate == null || rutToValidate.trim().isEmpty()) {
            return false;
        }

        if (!rutPattern.matcher(rutToValidate).matches()) {
            return false;
        }

        String[] separatedRut = rutToValidate.split("-");
        String rutNumberPart = separatedRut[0];
        char verifierDigit = separatedRut[1].toUpperCase().charAt(0);

        int accumulated = 0;
        int counter = 2;

        for (int i = rutNumberPart.length() - 1; i >= 0; i--) {
            accumulated += Integer.parseInt(String.valueOf(rutNumberPart.charAt(i))) * counter;
            counter++;
            if (counter == 8) {
                counter = 2;
            }
        }

        int remainder = accumulated % 11;
        char calculatedVerifierDigit = (char) (11 - remainder);

        if (calculatedVerifierDigit == 11) {
            calculatedVerifierDigit = '0';
        } else if (calculatedVerifierDigit == 10) {
            calculatedVerifierDigit = 'K';
        } else {
            calculatedVerifierDigit = (char) (calculatedVerifierDigit + '0');
        }

        return verifierDigit == calculatedVerifierDigit;

    }
}
