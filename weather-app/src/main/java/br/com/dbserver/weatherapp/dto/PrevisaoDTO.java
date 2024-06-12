package br.com.dbserver.weatherapp.dto;

import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;

import java.time.LocalDate;

public record PrevisaoDTO(
        Long id,
        String cidade,
        Turno turno,
        Clima clima,
        int temperaturaMinima,
        int temperaturaMaxima,
        int precipitacao,
        int umidade,
        int velocidadeVento,
        LocalDate data
) {}
