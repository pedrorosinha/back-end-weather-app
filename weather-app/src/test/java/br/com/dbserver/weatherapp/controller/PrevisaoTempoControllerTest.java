package br.com.dbserver.weatherapp.controller;

import br.com.dbserver.weatherapp.WeatherAppApplication;
import br.com.dbserver.weatherapp.controllers.PrevisaoTempoController;
import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;
import br.com.dbserver.weatherapp.services.interf.PrevisaoTempoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WeatherAppApplication.class)
class PrevisaoTempoControllerTest {

    @Mock
    private PrevisaoTempoService previsaoTempoService;

    @InjectMocks
    private PrevisaoTempoController previsaoTempoController;

    private String cidade;

    @BeforeEach
    void setUp() {
        cidade = "São Paulo";
    }

    @Test
    void testObterPrevisaoAtualSucesso() {
        PrevisaoDTO previsaoDTO = new PrevisaoDTO(1L, cidade, Turno.MANHA, Clima.ENSOLARADO, 25, 32, 5, 60, 10, LocalDate.now());

        when(previsaoTempoService.obterPrevisaoTempoAtual(cidade)).thenReturn(previsaoDTO);

        ResponseEntity<PrevisaoDTO> response = previsaoTempoController.obterPrevisaoAtual(cidade);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(previsaoDTO, response.getBody());
    }

    @Test
    void testObterPrevisaoProximos7Dias() {
        List<PrevisaoDTO> previsoesDTO = Arrays.asList(new PrevisaoDTO(1L, cidade, Turno.MANHA, Clima.CHUVOSO, 20, 25, 10, 80, 15, LocalDate.now().plusDays(1)), new PrevisaoDTO(2L, cidade, Turno.TARDE, Clima.NUBLADO, 22, 28, 5, 70, 10, LocalDate.now().plusDays(2)));

        when(previsaoTempoService.obterPrevisaoProximos7Dias(cidade)).thenReturn(previsoesDTO);

        ResponseEntity<List<PrevisaoDTO>> response = previsaoTempoController.obterPrevisaoProximos7Dias(cidade);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(previsoesDTO, response.getBody());
    }

    @Test
    void testObterTodasPrevisoes() {
        List<PrevisaoDTO> previsoesDTO = Arrays.asList(new PrevisaoDTO(1L, "São Paulo", Turno.MANHA, Clima.ENSOLARADO, 25, 32, 5, 60, 10, LocalDate.now()), new PrevisaoDTO(2L, "Porto Alegre", Turno.TARDE, Clima.CHUVOSO, 22, 28, 10, 85, 12, LocalDate.now()));

        when(previsaoTempoService.getAllPrevisoes()).thenReturn(previsoesDTO);

        ResponseEntity<List<PrevisaoDTO>> response = previsaoTempoController.obterTodasPrevisoes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(previsoesDTO, response.getBody());
    }

    @Test
    void testCadastrarPrevisaoSucesso() {
        PrevisaoDTO previsaoDTO = new PrevisaoDTO(1L, cidade, Turno.MANHA, Clima.CHUVOSO, 20, 28, 10, 70, 15, LocalDate.now());

        when(previsaoTempoService.cadastrarPrevisao(any(PrevisaoDTO.class))).thenReturn(previsaoDTO);

        ResponseEntity<PrevisaoDTO> response = previsaoTempoController.cadastrarPrevisao(previsaoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(previsaoDTO, response.getBody());
    }

    @Test
    void testAtualizarPrevisaoSucesso() {
        Long id = 1L;
        PrevisaoDTO previsaoDTO = new PrevisaoDTO(1L, cidade, Turno.TARDE, Clima.NUBLADO, 22, 30, 5, 65, 12, LocalDate.now());

        when(previsaoTempoService.atualizarPrevisao(eq(id), any(PrevisaoDTO.class))).thenReturn(previsaoDTO);

        ResponseEntity<PrevisaoDTO> response = previsaoTempoController.atualizarPrevisao(id, previsaoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(previsaoDTO, response.getBody());
    }

    @Test
    void testDeletarPrevisaoSucesso() {
        Long id = 1L;

        ResponseEntity<Void> response = previsaoTempoController.deletarPrevisao(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}