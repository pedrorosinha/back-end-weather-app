package br.com.dbserver.weatherapp.dto;

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
}
