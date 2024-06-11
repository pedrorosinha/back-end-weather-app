package br.com.dbserver.weatherapp.servicesTest;

import br.com.dbserver.weatherapp.WeatherAppApplication;
import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import br.com.dbserver.weatherapp.services.PrevisaoTempoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
    void getPrevisaoTempoAtual() {
        PrevisaoDTO resultado = previsaoTempoService.obterPrevisaoTempoAtual("São Paulo");

        assertEquals(previsaoDTO.getCidade(), resultado.getCidade());
        assertEquals(previsaoDTO.getClima(), resultado.getClima());
        assertEquals(previsaoDTO.getTurno(), resultado.getTurno());
    }

    @Test
    void obterPrevisaoProximos7Dias() {
        List<PrevisaoDTO> previsoes = previsaoTempoService.obterPrevisaoProximos7Dias("São Paulo");

        assertEquals(7, previsoes.size());
        assertEquals("São Paulo", previsoes.get(0).getCidade());
    }

    @Test
    void getAllPrevisoes() {
        List<PrevisaoTempo> previsoes = Arrays.asList(new PrevisaoTempo(1L, "São Paulo", Turno.MANHA, Clima.ENSOLARADO, 25, 32, 5, 60, 10, LocalDate.now()), new PrevisaoTempo(2L, "Porto Alegre", Turno.TARDE, Clima.CHUVOSO, 20, 28, 10, 80, 15, LocalDate.now()));

        when(previsaoTempoRepository.findAll()).thenReturn(previsoes);

        List<PrevisaoDTO> resultado = previsaoTempoService.getAllPrevisoes();

        assertEquals(2, resultado.size());
        assertEquals("São Paulo", resultado.get(0).getCidade());
        assertEquals("Porto Alegre", resultado.get(1).getCidade());
    }

    @Test
    void cadastrarPrevisao() {
        previsaoTempoService.cadastrarPrevisao(previsaoDTO);

        verify(previsaoTempoRepository, times(1)).save(any(PrevisaoTempo.class));
    }

    @Test
    void atualizarPrevisao() {
        Long id = 1L;
        PrevisaoTempo previsaoTempoAtual = new PrevisaoTempo(id, "São Paulo", Turno.MANHA, Clima.CHUVOSO, 18,24,8,85,12, LocalDate.now());

        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(previsaoTempoAtual));

        PrevisaoDTO previsaoAtualizada = new PrevisaoDTO(1L, "POA", Turno.NOITE, Clima.NUBLADO, 22, 30, 3, 65, 12, LocalDate.now());

        PrevisaoDTO resultado = previsaoTempoService.atualizarPrevisao(id, previsaoAtualizada);

        assertEquals(previsaoAtualizada.getCidade(), resultado.getCidade());
        assertEquals(previsaoAtualizada.getClima(), resultado.getClima());

        verify(previsaoTempoRepository, times(1)).save(any(PrevisaoTempo.class));
    }

    @Test
    void deletarPrevisao() {
        Long id = 1L;

        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(new PrevisaoTempo(id, "São Paulo", Turno.MANHA, Clima.ENSOLARADO, 25, 32, 5, 60, 10, LocalDate.now())));

        previsaoTempoService.deletarPrevisao(id);

        verify(previsaoTempoRepository, times(1)).deleteById(id);
    }
}