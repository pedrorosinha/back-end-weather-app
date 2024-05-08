package br.com.dbserver.weatherapp.dto;

import java.util.Objects;

public class PrevisaoDTO {

    private String cidade;
    private String tempo;

    public PrevisaoDTO() {
    }

    public PrevisaoDTO(String cidade, String tempo) {
        this.cidade = cidade;
        this.tempo = tempo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrevisaoDTO that = (PrevisaoDTO) o;
        return Objects.equals(cidade, that.cidade) && Objects.equals(tempo, that.tempo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cidade, tempo);
    }
}
