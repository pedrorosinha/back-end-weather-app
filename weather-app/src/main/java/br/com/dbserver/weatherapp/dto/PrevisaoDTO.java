package br.com.dbserver.weatherapp.dto;

import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;
import br.com.dbserver.weatherapp.validation.ValidaTemperatura;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@ValidaTemperatura
public record PrevisaoDTO(
        @NotNull(message = "Id não pode ser nulo")
        Long id,
        @NotNull(message = "O nome da cidade não pode ser vazia")
        String cidade,
        @NotNull(message = "O turno não pode ser nulo")
        Turno turno,
        @NotNull(message = "O clima não pode ser nulo")
        Clima clima,

        int temperaturaMinima,
        int temperaturaMaxima,
        @NotBlank
        int precipitacao,
        @NotBlank
        int umidade,
        @NotBlank
        int velocidadeVento,
        LocalDate data
) {}
