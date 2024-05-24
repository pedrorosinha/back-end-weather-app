package br.com.dbserver.weatherapp.model;

import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PrevisaoTempo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cidade;
    private Turno turno;
    private Clima clima;
    private int temperaturaMinima;
    private int temperaturaMaxima;
    private int precipitacao;
    private int umidade;
    private int velocidadeVento;

    public PrevisaoTempo(Long id, String cidade, Turno turno, Clima clima, int temperaturaMinima, int temperaturaMaxima, int precipitacao, int umidade, int velocidadeVento) {
        this.id = id;
        this.cidade = cidade;
        this.turno = turno;
        this.clima = clima;
        this.temperaturaMinima = temperaturaMinima;
        this.temperaturaMaxima = temperaturaMaxima;
        this.precipitacao = precipitacao;
        this.umidade = umidade;
        this.velocidadeVento = velocidadeVento;
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

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Clima getClima() {
        return clima;
    }

    public void setClima(Clima clima) {
        this.clima = clima;
    }

    public int getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(int temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public int getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(int temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public int getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(int precipitacao) {
        this.precipitacao = precipitacao;
    }

    public int getUmidade() {
        return umidade;
    }

    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

    public int getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(int velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }
}
