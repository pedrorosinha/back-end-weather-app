package br.com.dbserver.weatherapp.model;

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
    private String tempo;

    public PrevisaoTempo(Long id, String cidade, String tempo) {
        this.id = id;
        this.cidade = cidade;
        this.tempo = tempo;
    }

    public PrevisaoTempo() {
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Long getId() {
        return id;
    }
}
