package br.com.dbserver.weatherapp.services;

import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import br.com.dbserver.weatherapp.services.interf.PrevisaoTempoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrevisaoTempoServiceImpl implements PrevisaoTempoService {

    private final PrevisaoTempoRepository previsaoTempoRepository;

    @Autowired
    public PrevisaoTempoServiceImpl(PrevisaoTempoRepository previsaoTempoRepository) {
        this.previsaoTempoRepository = previsaoTempoRepository;
    }

    @Override
    public PrevisaoDTO obterPrevisaoTempoAtual(String cidade) {
        return new PrevisaoDTO(cidade, "Ensolarado");
    }

    @Override
    public List<PrevisaoDTO> obterPrevisaoProximos7Dias(String cidade) {
        return List.of(new PrevisaoDTO(cidade, "Chuvoso"), new PrevisaoDTO(cidade, "Nublado"));
    }

    @Override
    public List<PrevisaoTempo> getAllPrevisoes() {
        return previsaoTempoRepository.findAll();
    }

    @Override
    public List<PrevisaoDTO> getAllPrevisoesAsDTO() {
        List<PrevisaoTempo> previsoes = previsaoTempoRepository.findAll();
        return previsoes.stream().map(previsao -> new PrevisaoDTO(previsao.getCidade(), previsao.getTempo())).collect(Collectors.toList());
    }

    @Override
    public PrevisaoDTO cadastrarPrevisao(PrevisaoDTO previsaoDTO) {
        PrevisaoTempo previsaoTempo = new PrevisaoTempo();
        previsaoTempo.setCidade(previsaoDTO.getCidade());
        previsaoTempo.setTempo(previsaoDTO.getTempo());
        previsaoTempoRepository.save(previsaoTempo);
        return previsaoDTO;
    }

    @Override
    public PrevisaoDTO atualizarPrevisao(Long id, PrevisaoDTO previsaoDTO) {
        PrevisaoTempo previsaoTempo = previsaoTempoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Previsão não encontrada para o ID: " + id));

        previsaoTempo.setCidade(previsaoDTO.getCidade());
        previsaoTempo.setTempo(previsaoDTO.getTempo());
        previsaoTempoRepository.save(previsaoTempo);

        return new PrevisaoDTO(previsaoTempo.getCidade(), previsaoTempo.getTempo());
    }

    @Override
    public void deletarPrevisao(Long id) {
        previsaoTempoRepository.deleteById(id);
    }
}