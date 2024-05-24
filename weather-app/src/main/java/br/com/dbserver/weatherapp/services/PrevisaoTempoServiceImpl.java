package br.com.dbserver.weatherapp.services;

import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;
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
        return new PrevisaoDTO(cidade, Turno.MANHA, Clima.ENSOLARADO, 20, 30, 0, 70, 10);
    }

    @Override
    public List<PrevisaoDTO> obterPrevisaoProximos7Dias(String cidade) {
        return List.of(new PrevisaoDTO(cidade, Turno.TARDE, Clima.CHUVOSO, 18, 25, 10, 80, 15), new PrevisaoDTO(cidade, Turno.NOITE, Clima.NUBLADO, 15, 22, 5, 75, 12));
    }

    @Override
    public List<PrevisaoDTO> getAllPrevisoes() {
        List<PrevisaoTempo> previsoes = previsaoTempoRepository.findAll();
        return previsoes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PrevisaoDTO cadastrarPrevisao(PrevisaoDTO previsaoDTO) {
        PrevisaoTempo previsaoTempo = convertToEntity(previsaoDTO);
        previsaoTempoRepository.save(previsaoTempo);
        return convertToDTO(previsaoTempo);
    }

    @Override
    public PrevisaoDTO atualizarPrevisao(Long id, PrevisaoDTO previsaoDTO) {
        PrevisaoTempo previsaoTempo = previsaoTempoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Previsão não encontrada para o ID: " + id));

        previsaoTempo.setCidade(previsaoDTO.getCidade());
        previsaoTempo.setTurno(previsaoDTO.getTurno());
        previsaoTempo.setClima(previsaoDTO.getClima());
        previsaoTempo.setTemperaturaMinima(previsaoDTO.getTemperaturaMinima());
        previsaoTempo.setTemperaturaMaxima(previsaoDTO.getTemperaturaMaxima());
        previsaoTempo.setPrecipitacao(previsaoDTO.getPrecipitacao());
        previsaoTempo.setUmidade(previsaoDTO.getUmidade());
        previsaoTempo.setVelocidadeVento(previsaoDTO.getVelocidadeVento());

        previsaoTempoRepository.save(previsaoTempo);

        return convertToDTO(previsaoTempo);
    }

    @Override
    public void deletarPrevisao(Long id) {
        previsaoTempoRepository.deleteById(id);
    }

    private PrevisaoDTO convertToDTO(PrevisaoTempo previsaoTempo) {
        return new PrevisaoDTO(previsaoTempo.getCidade(), previsaoTempo.getTurno(), previsaoTempo.getClima(), previsaoTempo.getTemperaturaMinima(), previsaoTempo.getTemperaturaMaxima(), previsaoTempo.getPrecipitacao(), previsaoTempo.getUmidade(), previsaoTempo.getVelocidadeVento());
    }

    private PrevisaoTempo convertToEntity(PrevisaoDTO previsaoDTO) {
        return new PrevisaoTempo(null, previsaoDTO.getCidade(), previsaoDTO.getTurno(), previsaoDTO.getClima(), previsaoDTO.getTemperaturaMinima(), previsaoDTO.getTemperaturaMaxima(), previsaoDTO.getPrecipitacao(), previsaoDTO.getUmidade(), previsaoDTO.getVelocidadeVento());
    }
}