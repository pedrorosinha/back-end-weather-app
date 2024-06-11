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
    public PrevisaoDTO obterPrevisaoTempoAtual(String cidade) {
        return new PrevisaoDTO(null, cidade, Turno.MANHA, Clima.ENSOLARADO, 25, 32, 5, 60, 10, LocalDate.now());
    }

    @Override
    public List<PrevisaoDTO> obterPrevisaoProximos7Dias(String cidade) {
        List<PrevisaoDTO> previsoes = new ArrayList<>();
        LocalDate data = LocalDate.now().plusDays(1);
        for (int i = 0; i < 7; i++) {
            previsoes.add(new PrevisaoDTO(null, cidade, Turno.values()[i % 3], Clima.values()[i % 4], 20 + i, 30 - i, i * 5, 70 - i, 10 + i, data.plusDays(i)));
        }
        return previsoes;
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

        previsaoTempo.setCidade(previsaoDTO.getCidade());
        previsaoTempo.setTurno(previsaoDTO.getTurno());
        previsaoTempo.setClima(previsaoDTO.getClima());
        previsaoTempo.setTemperaturaMinima(previsaoDTO.getTemperaturaMinima());
        previsaoTempo.setTemperaturaMaxima(previsaoDTO.getTemperaturaMaxima());
        previsaoTempo.setPrecipitacao(previsaoDTO.getPrecipitacao());
        previsaoTempo.setUmidade(previsaoDTO.getUmidade());
        previsaoTempo.setVelocidadeVento(previsaoDTO.getVelocidadeVento());
        previsaoTempo.setData(previsaoDTO.getData());

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
        return new PrevisaoTempo(null, previsaoDTO.getCidade(), previsaoDTO.getTurno(), previsaoDTO.getClima(), previsaoDTO.getTemperaturaMinima(), previsaoDTO.getTemperaturaMaxima(), previsaoDTO.getPrecipitacao(), previsaoDTO.getUmidade(), previsaoDTO.getVelocidadeVento(), previsaoDTO.getData());
    }
}