package br.com.zupacademy.proposta.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class ValidaBase64Validator implements ConstraintValidator<ValidaBase64, String> {

    @Override
    public void initialize(ValidaBase64 constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            decoder.decode(value.getBytes());
            return true;
        } catch(IllegalArgumentException exception) {
            return false;
        }
    }
}
