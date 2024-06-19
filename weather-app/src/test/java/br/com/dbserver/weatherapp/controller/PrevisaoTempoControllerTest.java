package br.com.dbserver.weatherapp.controller;

import br.com.dbserver.weatherapp.WeatherAppApplication;
import br.com.dbserver.weatherapp.controllers.PrevisaoTempoController;
import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.services.interf.PrevisaoTempoService;
import br.com.dbserver.weatherapp.utils.PrevisaoFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WeatherAppApplication.class)
@ActiveProfiles("test")
class PrevisaoTempoControllerTest {

    @Mock
    private PrevisaoTempoService previsaoTempoService;

    @InjectMocks
    private PrevisaoTempoController previsaoTempoController;

    private PrevisaoDTO previsaoDTO;
    private String cidade;

    @BeforeEach
    void setUp() {
        cidade = "São Paulo";
        previsaoDTO = PrevisaoFixture.criarPrevisaoDTO();
    }

    @Test
    void testObterTodasPrevisoes() {
        List<PrevisaoDTO> previsoesDTO = PrevisaoFixture.criarListaPrevisaoDTO(2);

        when(previsaoTempoService.getAllPrevisoes()).thenReturn(previsoesDTO);

        ResponseEntity<List<PrevisaoDTO>> response = previsaoTempoController.obterTodasPrevisoes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(previsoesDTO, response.getBody());
    }

    @Test
    void testObterTodasPrevisoesPorCidade() {
        List<PrevisaoDTO> previsoesDTO = PrevisaoFixture.criarListaPrevisaoDTO(2);

        when(previsaoTempoService.obterTodasPrevisoesPorCidade(eq(cidade))).thenReturn(previsoesDTO);

        ResponseEntity<List<PrevisaoDTO>> response = previsaoTempoController.obterTodasPrevisoesPorCidade(cidade);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(previsoesDTO, response.getBody());
    }

    @Test
    void testCadastrarPrevisaoSucesso() {
        when(previsaoTempoService.cadastrarPrevisao(any(PrevisaoDTO.class))).thenReturn(previsaoDTO);

        ResponseEntity<PrevisaoDTO> response = previsaoTempoController.cadastrarPrevisao(previsaoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(previsaoDTO, response.getBody());
    }

    @Test
    void testAtualizarPrevisaoSucesso() {
        Long id = 1L;

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