package br.com.dbserver.weatherapp.servicesTest;

import br.com.dbserver.weatherapp.WeatherAppApplication;
import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import br.com.dbserver.weatherapp.services.PrevisaoTempoServiceImpl;
import br.com.dbserver.weatherapp.utils.PrevisaoFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = WeatherAppApplication.class)
@ActiveProfiles("test")
public class PrevisaoTempoServiceTest {

    @Mock
    private PrevisaoTempoRepository previsaoTempoRepository;

    @InjectMocks
    private PrevisaoTempoServiceImpl previsaoTempoService;

    private PrevisaoDTO previsaoDTO;

    @BeforeEach
    void setUp() {
        previsaoDTO = PrevisaoFixture.criarPrevisaoDTO();
    }

    @Test
    @DisplayName("Deve pegar todas as previsões de uma cidade")
    void obterTodasPrevisoesPorCidade() {
        String cidade = previsaoDTO.cidade();

        List<PrevisaoTempo> previsaoTempoList = PrevisaoFixture.criarListaPrevisaoTempo(2);

        when(previsaoTempoRepository.findByCidade(cidade)).thenReturn(previsaoTempoList);

        List<PrevisaoDTO> resultado = previsaoTempoService.obterTodasPrevisoesPorCidade(cidade);

        assertEquals(previsaoTempoList.size(), resultado.size());
        assertEquals(previsaoTempoList.get(0).getCidade(), resultado.get(0).cidade());
        assertEquals(previsaoTempoList.get(0).getClima(), resultado.get(0).clima());
    }

    @Test
    @DisplayName("Deve pegar todas as previsões de todas as cidades")
    void getAllPrevisoes() {
        List<PrevisaoTempo> previsoes = PrevisaoFixture.criarListaPrevisaoTempo(2);

        when(previsaoTempoRepository.findAll()).thenReturn(previsoes);

        List<PrevisaoDTO> resultado = previsaoTempoService.getAllPrevisoes();

        assertEquals(previsoes.size(), resultado.size());
        assertEquals(previsoes.get(0).getCidade(), resultado.get(0).cidade());
    }

    @Test
    @DisplayName("Deve cadastrar uma previsão")
    void cadastrarPrevisao() {
        previsaoTempoService.cadastrarPrevisao(previsaoDTO);

        verify(previsaoTempoRepository, times(1)).save(any(PrevisaoTempo.class));
    }

    @Test
    @DisplayName("Deve atualizar a previsão")
    void atualizarPrevisao() {
        Long id = previsaoDTO.id();
        PrevisaoTempo previsaoTempoAtual = PrevisaoFixture.criarPrevisaoTempo();

        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(previsaoTempoAtual));

        PrevisaoDTO previsaoAtualizada = PrevisaoFixture.criarPrevisaoDTO();

        PrevisaoDTO resultado = previsaoTempoService.atualizarPrevisao(id, previsaoAtualizada);

        assertEquals(previsaoAtualizada.cidade(), resultado.cidade());
        assertEquals(previsaoAtualizada.clima(), resultado.clima());

        verify(previsaoTempoRepository, times(1)).save(any(PrevisaoTempo.class));
    }

    @Test
    @DisplayName("Deve deletar uma previsão")
    void deletarPrevisao() {
        Long id = previsaoDTO.id();

        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(PrevisaoFixture.criarPrevisaoTempo()));

        previsaoTempoService.deletarPrevisao(id);

        verify(previsaoTempoRepository, times(1)).deleteById(id);
    }
}