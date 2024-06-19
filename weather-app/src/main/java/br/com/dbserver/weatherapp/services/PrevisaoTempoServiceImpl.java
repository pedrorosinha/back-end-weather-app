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

import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<PrevisaoDTO> getAllPrevisoes() {
        List<PrevisaoTempo> previsoes = previsaoTempoRepository.findAll();
        return previsoes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PrevisaoDTO> obterTodasPrevisoesPorCidade(String cidade) {
        List<PrevisaoTempo> previsoes = previsaoTempoRepository.findByCidade(cidade);
        return previsoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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

        previsaoTempo.setCidade(previsaoDTO.cidade());
        previsaoTempo.setTurno(previsaoDTO.turno());
        previsaoTempo.setClima(previsaoDTO.clima());
        previsaoTempo.setTemperaturaMinima(previsaoDTO.temperaturaMinima());
        previsaoTempo.setTemperaturaMaxima(previsaoDTO.temperaturaMaxima());
        previsaoTempo.setPrecipitacao(previsaoDTO.precipitacao());
        previsaoTempo.setUmidade(previsaoDTO.umidade());
        previsaoTempo.setVelocidadeVento(previsaoDTO.velocidadeVento());
        previsaoTempo.setData(previsaoDTO.data());

        previsaoTempoRepository.save(previsaoTempo);

        return convertToDTO(previsaoTempo);
    }

    @Override
    public void deletarPrevisao(Long id) {
        previsaoTempoRepository.deleteById(id);
    }

    private PrevisaoDTO convertToDTO(PrevisaoTempo previsaoTempo) {
        return new PrevisaoDTO(previsaoTempo.getId(), previsaoTempo.getCidade(), previsaoTempo.getTurno(), previsaoTempo.getClima(), previsaoTempo.getTemperaturaMinima(), previsaoTempo.getTemperaturaMaxima(), previsaoTempo.getPrecipitacao(), previsaoTempo.getUmidade(), previsaoTempo.getVelocidadeVento(), previsaoTempo.getData());
    }

    private PrevisaoTempo convertToEntity(PrevisaoDTO previsaoDTO) {
        return PrevisaoTempo.builder()
                .cidade(previsaoDTO.cidade())
                .turno(previsaoDTO.turno())
                .clima(previsaoDTO.clima())
                .temperaturaMinima(previsaoDTO.temperaturaMinima())
                .temperaturaMaxima(previsaoDTO.temperaturaMaxima())
                .precipitacao(previsaoDTO.precipitacao())
                .umidade(previsaoDTO.umidade())
                .velocidadeVento(previsaoDTO.velocidadeVento())
                .data(previsaoDTO.data())
                .build();
    }
}