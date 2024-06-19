package br.com.dbserver.weatherapp.validation;

import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TemperaturaValidator implements ConstraintValidator<ValidaTemperatura, PrevisaoDTO> {

    @Override
    public void initialize(ValidaTemperatura constraintAnnotation) {
    }

    @Override
    public boolean isValid(PrevisaoDTO previsaoDTO, ConstraintValidatorContext context) {
        if (previsaoDTO == null) {
            return true; // null values should be handled by @NotNull or @NotBlank
        }

        return previsaoDTO.temperaturaMinima() <= previsaoDTO.temperaturaMaxima();
    }
}
