package br.com.dbserver.weatherapp.services.interf;

import br.com.dbserver.weatherapp.model.PrevisaoTempo;

import java.util.List;

public interface PrevisaoTempoService {
    String getPrevisaoTempoAtual(String cidade);
    String getPrevisaoProximos7Dias(String cidade);
    List<PrevisaoTempo> getAllPrevisoes();
    void cadastrarPrevisao(PrevisaoTempo previsaoTempo);
    void editarPrevisao(Long id, PrevisaoTempo previsaoTempo);
    void deletarPrevisao(Long id);
}
