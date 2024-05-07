package br.com.dbserver.weatherapp.services.interf;

import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;

import java.util.List;

public interface PrevisaoTempoService {
    PrevisaoDTO obterPrevisaoTempoAtual(String cidade);
    List<PrevisaoDTO> obterPrevisaoProximos7Dias(String cidade);
    List<PrevisaoTempo> getAllPrevisoes();

    List<PrevisaoDTO> getAllPrevisoesAsDTO();

    PrevisaoDTO cadastrarPrevisao(PrevisaoTempo previsaoDTO);
    PrevisaoDTO atualizarPrevisao(Long id, PrevisaoDTO previsaoDTO);
    void deletarPrevisao(Long id);
}
