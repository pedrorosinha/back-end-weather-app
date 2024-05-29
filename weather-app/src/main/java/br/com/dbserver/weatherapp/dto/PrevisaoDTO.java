package br.com.dbserver.weatherapp.dto;

import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PrevisaoDTO {
    private String cidade;
    private Turno turno;
    private Clima clima;
    private int temperaturaMinima;
    private int temperaturaMaxima;
    private int precipitacao;
    private int umidade;
    private int velocidadeVento;

    private LocalDate data;

    public PrevisaoDTO(String cidade, Turno turno, Clima clima, int temperaturaMinima, int temperaturaMaxima, int precipitacao, int umidade, int velocidadeVento, LocalDate data) {
        this.cidade = cidade;
        this.turno = turno;
        this.clima = clima;
        this.temperaturaMinima = temperaturaMinima;
        this.temperaturaMaxima = temperaturaMaxima;
        this.precipitacao = precipitacao;
        this.umidade = umidade;
        this.velocidadeVento = velocidadeVento;
        this.data = data;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Turno getTurno() {
        return turno;
    }

    public Clima getClima() {
        return clima;
    }


    public int getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public int getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public int getPrecipitacao() {
        return precipitacao;
    }

    public int getUmidade() {
        return umidade;
    }

    public int getVelocidadeVento() {
        return velocidadeVento;
    }

    public LocalDate getData() {
        return data;
    }
}
