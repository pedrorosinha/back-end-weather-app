package br.com.dbserver.weatherapp.services.interf;

import br.com.dbserver.weatherapp.dto.PrevisaoDTO;

import java.util.List;

public interface PrevisaoTempoService {
    List<PrevisaoDTO> getAllPrevisoes();

    List<PrevisaoDTO> obterTodasPrevisoesPorCidade(String cidade);

    PrevisaoDTO cadastrarPrevisao(PrevisaoDTO previsaoDTO);

    PrevisaoDTO atualizarPrevisao(Long id, PrevisaoDTO previsaoDTO);

    void deletarPrevisao(Long id);
}