package br.com.dbserver.weatherapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TemperaturaValidator.class)
public @interface ValidaTemperatura {
    String message() default "Temperatura mínima não pode ser maior que temperatura máxima";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
