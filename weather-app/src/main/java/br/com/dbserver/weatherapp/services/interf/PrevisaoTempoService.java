package br.com.dbserver.weatherapp.services.interf;

import br.com.dbserver.weatherapp.dto.PrevisaoDTO;

import java.util.List;

public interface PrevisaoTempoService {
    PrevisaoDTO obterPrevisaoTempoAtual(String cidade);

    List<PrevisaoDTO> obterPrevisaoProximos7Dias(String cidade);

    List<PrevisaoDTO> getAllPrevisoes();

    PrevisaoDTO cadastrarPrevisao(PrevisaoDTO previsaoDTO);

    PrevisaoDTO atualizarPrevisao(Long id, PrevisaoDTO previsaoDTO);

    void deletarPrevisao(Long id);
}