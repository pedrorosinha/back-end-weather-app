package br.com.dbserver.weatherapp.servicesTest;

import br.com.dbserver.weatherapp.WeatherAppApplication;
import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import br.com.dbserver.weatherapp.services.PrevisaoTempoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = WeatherAppApplication.class)
public class PrevisaoTempoServiceTest {

    @Mock
    private PrevisaoTempoRepository previsaoTempoRepository;

    @InjectMocks
    private PrevisaoTempoServiceImpl previsaoTempoService;

    private PrevisaoDTO previsaoDTO;

    @BeforeEach
    void setUp() {
        previsaoDTO = new PrevisaoDTO(1L, "São Paulo", Turno.MANHA, Clima.ENSOLARADO, 25, 32, 5, 60, 10, LocalDate.now());
    }

    @Test
    @DisplayName("Deve pegar a previsão do tempo atual")
    void getPrevisaoTempoAtual() {
        PrevisaoDTO resultado = previsaoTempoService.obterPrevisaoTempoAtual("São Paulo");

        assertEquals(previsaoDTO.cidade(), resultado.cidade());
        assertEquals(previsaoDTO.clima(), resultado.clima());
        assertEquals(previsaoDTO.turno(), resultado.turno());
    }

    @Test
    @DisplayName("Deve pegar as previsões dos próximos 7 dias")
    void obterPrevisaoProximos7Dias() {
        List<PrevisaoDTO> previsoes = previsaoTempoService.obterPrevisaoProximos7Dias("São Paulo");

        assertEquals(7, previsoes.size());
        assertEquals("São Paulo", previsoes.get(0).cidade());
    }

    @Test
    @DisplayName("Deve pegar todas as previsões de uma cidade")
    void obterTodasPrevisoesPorCidade() {
        String cidade = "São Paulo";
        PrevisaoTempo previsao1 = new PrevisaoTempo(1L, cidade, Turno.MANHA, Clima.ENSOLARADO, 20, 30, 0, 50, 10, LocalDate.now());
        PrevisaoTempo previsao2 = new PrevisaoTempo(2L, cidade, Turno.TARDE, Clima.CHUVOSO, 22, 32, 5, 60, 12, LocalDate.now().plusDays(1));

        List<PrevisaoTempo> previsoesList = List.of(previsao1, previsao2);

        when(previsaoTempoRepository.findByCidade(cidade)).thenReturn(previsoesList);

        List<PrevisaoDTO> result = previsaoTempoService.obterTodasPrevisoesPorCidade(cidade);

        assertEquals(2, result.size());
        assertEquals("São Paulo", result.get(0).cidade());
        assertEquals(Clima.ENSOLARADO, result.get(0).clima());
        assertEquals("São Paulo", result.get(1).cidade());
        assertEquals(Clima.CHUVOSO, result.get(1).clima());
    }

    @Test
    @DisplayName("Deve pegar todas as previsões de todas as cidades")
    void getAllPrevisoes() {
        List<PrevisaoTempo> previsoes = Arrays.asList(new PrevisaoTempo(1L, "São Paulo", Turno.MANHA, Clima.ENSOLARADO, 25, 32, 5, 60, 10, LocalDate.now()), new PrevisaoTempo(2L, "Porto Alegre", Turno.TARDE, Clima.CHUVOSO, 20, 28, 10, 80, 15, LocalDate.now()));

        when(previsaoTempoRepository.findAll()).thenReturn(previsoes);

        List<PrevisaoDTO> resultado = previsaoTempoService.getAllPrevisoes();

        assertEquals(2, resultado.size());
        assertEquals("São Paulo", resultado.get(0).cidade());
        assertEquals("Porto Alegre", resultado.get(1).cidade());
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
        Long id = 1L;
        PrevisaoTempo previsaoTempoAtual = new PrevisaoTempo(id, "São Paulo", Turno.MANHA, Clima.CHUVOSO, 18, 24, 8, 85, 12, LocalDate.now());

        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(previsaoTempoAtual));

        PrevisaoDTO previsaoAtualizada = new PrevisaoDTO(1L, "POA", Turno.NOITE, Clima.NUBLADO, 22, 30, 3, 65, 12, LocalDate.now());

        PrevisaoDTO resultado = previsaoTempoService.atualizarPrevisao(id, previsaoAtualizada);

        assertEquals(previsaoAtualizada.cidade(), resultado.cidade());
        assertEquals(previsaoAtualizada.clima(), resultado.clima());

        verify(previsaoTempoRepository, times(1)).save(any(PrevisaoTempo.class));
    }

    @Test
    @DisplayName("Deve deletar uma previsão")
    void deletarPrevisao() {
        Long id = 1L;

        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(new PrevisaoTempo(id, "São Paulo", Turno.MANHA, Clima.ENSOLARADO, 25, 32, 5, 60, 10, LocalDate.now())));

        previsaoTempoService.deletarPrevisao(id);

        verify(previsaoTempoRepository, times(1)).deleteById(id);
    }
}