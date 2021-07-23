package br.com.zupacademy.proposta.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidaBase64Validator.class)
public @interface ValidaBase64 {

    String message() default "não atende ao formato válido.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}