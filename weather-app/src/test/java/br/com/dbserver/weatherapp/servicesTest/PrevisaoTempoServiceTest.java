package br.com.dbserver.weatherapp.servicesTest;

import br.com.dbserver.weatherapp.WeatherAppApplication;
import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import br.com.dbserver.weatherapp.services.PrevisaoTempoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = WeatherAppApplication.class)
public class PrevisaoTempoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PrevisaoTempoRepository previsaoTempoRepository;

    @InjectMocks
    private PrevisaoTempoServiceImpl previsaoTempoService;

    private String cidade;
    private String previsao;

    @BeforeEach
    void setUp() {
        cidade = "São Paulo";
        previsao = "Ensolarado";
    }

    @Test
    void getPrevisaoTempoAtual() {
        PrevisaoDTO previsaoDTO = new PrevisaoDTO(cidade, previsao);

        when(restTemplate.getForObject(anyString(), eq(PrevisaoDTO.class))).thenReturn(previsaoDTO);

        PrevisaoDTO resultado = previsaoTempoService.obterPrevisaoTempoAtual(cidade);

        assertEquals(previsaoDTO, resultado);
    }

    @Test
    void getPrevisaoProximos7Dias() {
        List<PrevisaoDTO> previsoesDTO = Arrays.asList(new PrevisaoDTO(cidade, "Chuvoso"), new PrevisaoDTO(cidade, "Nublado"));

        when(previsaoTempoService.obterPrevisaoProximos7Dias(anyString())).thenReturn(previsoesDTO);

        List<PrevisaoDTO> resultado = previsaoTempoService.obterPrevisaoProximos7Dias(cidade);

        assertEquals(2, resultado.size());
        assertEquals("Chuvoso", resultado.get(0).getTempo());
        assertEquals("Nublado", resultado.get(1).getTempo());
    }

    @Test
    void getAllPrevisoes() {
        List<PrevisaoTempo> previsoes = Arrays.asList(new PrevisaoTempo(1L, cidade, "Chuvoso"), new PrevisaoTempo(2L, "Porto Alegre", "Chuvoso"));

        when(previsaoTempoRepository.findAll()).thenReturn(previsoes);

        List<PrevisaoTempo> resultado = previsaoTempoService.getAllPrevisoes();

        assertEquals(2, resultado.size());
        assertEquals(cidade, resultado.get(0).getCidade());
        assertEquals("Porto Alegre", resultado.get(1).getCidade());
    }

    @Test
    void cadastrarPrevisao() {
        PrevisaoDTO previsaoDTO = new PrevisaoDTO(cidade, previsao);
        PrevisaoTempo previsaoTempoEsperado = new PrevisaoTempo(null, cidade, previsao);

        previsaoTempoService.cadastrarPrevisao(previsaoDTO);

        verify(previsaoTempoRepository, times(1)).save(any(PrevisaoTempo.class));
    }

    @Test
    void atualizarPrevisao() {
        Long id = 1L;
        PrevisaoDTO previsaoDTO = new PrevisaoDTO("POA", "Ensolarado");

        PrevisaoTempo previsaoTempoExistente = new PrevisaoTempo(id, "São Paulo", "Chuvoso");
        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(previsaoTempoExistente));

        PrevisaoDTO resultado = previsaoTempoService.atualizarPrevisao(id, previsaoDTO);

        assertEquals("POA", resultado.getCidade());
        assertEquals("Ensolarado", resultado.getTempo());

        verify(previsaoTempoRepository, times(1)).save(any(PrevisaoTempo.class));
    }

    @Test
    void deletarPrevisao() {
        Long id = 1L;

        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(new PrevisaoTempo(id, cidade, previsao)));

        previsaoTempoService.deletarPrevisao(id);

        verify(previsaoTempoRepository, times(1)).deleteById(id);
    }
}