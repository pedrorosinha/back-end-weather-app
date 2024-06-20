package br.com.dbserver.weatherapp.dto;

import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;
import br.com.dbserver.weatherapp.validation.ValidaTemperatura;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@ValidaTemperatura
public record PrevisaoDTO(
        Long id,
        @NotBlank(message = "O nome da cidade não pode ser vazia")
        String cidade,
        @NotNull(message = "O turno não pode ser nulo")
        Turno turno,
        @NotNull(message = "O clima não pode ser nulo")
        Clima clima,

        int temperaturaMinima,
        int temperaturaMaxima,
        @PositiveOrZero(message = "A precipitação deve ser um valor positivo ou zero")
        int precipitacao,
        @PositiveOrZero(message = "A umidade deve ser um valor positivo ou zero")
        int umidade,
        @PositiveOrZero(message = "A velocidade do vento deve ser um valor positivo ou zero")
        int velocidadeVento,
        @NotNull
        LocalDate data
) {}
