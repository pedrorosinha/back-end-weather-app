package br.com.dbserver.weatherapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrevisaoTempoDTO {
    private String cidade;
    private String tempo;


}
