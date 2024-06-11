package br.com.dbserver.weatherapp.dto;

import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;

import java.time.LocalDate;

public class PrevisaoDTO {
    private Long id;
    private String cidade;
    private Turno turno;
    private Clima clima;
    private int temperaturaMinima;
    private int temperaturaMaxima;
    private int precipitacao;
    private int umidade;
    private int velocidadeVento;
    private LocalDate data;

    public PrevisaoDTO(Long id, String cidade, Turno turno, Clima clima, int temperaturaMinima, int temperaturaMaxima, int precipitacao, int umidade, int velocidadeVento, LocalDate data) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
